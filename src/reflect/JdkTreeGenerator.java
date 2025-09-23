package reflect;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class JdkTreeGenerator {

    // Узел дерева
    static class Node {
        String name;
        String type;
        List<Node> children = new ArrayList<>();

        Node(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    public static void main(String[] args) throws Exception {
        // Укажем пакеты, которые хотим разобрать
        String[] packages = {
                "java.lang",
                "java.util",
                "java.util.concurrent"
        };

        for (String pkg : packages) {
            System.out.println("\n=== " + pkg + " ===");
            generateTree(pkg);
        }
    }

    private static void generateTree(String packageName) throws Exception {
        // Получаем все классы пакета через ClassLoader (упрощённо, вручную перечислять нельзя)
        // В реальной версии можно подключить ClassGraph или Reflections
        // Для примера – хардкодим несколько классов
        List<Class<?>> classes = new ArrayList<>();

        if (packageName.equals("java.lang")) {
            classes = List.of(Object.class, String.class, Thread.class, Runnable.class, Enum.class);
        } else if (packageName.equals("java.util")) {
            classes = List.of(Collection.class, List.class, Set.class, Map.class,
                    ArrayList.class, LinkedList.class, HashSet.class, TreeSet.class, HashMap.class);
        } else if (packageName.equals("java.util.concurrent")) {
            classes = List.of(Executor.class, ExecutorService.class, Future.class, Callable.class,
                    ThreadPoolExecutor.class, ForkJoinPool.class, ConcurrentHashMap.class);
        }

        // Карта: класс → дети
        Map<Class<?>, List<Class<?>>> hierarchy = new HashMap<>();
        for (Class<?> cls : classes) {
            Class<?> superClass = cls.getSuperclass();
            if (superClass != null && classes.contains(superClass)) {
                hierarchy.computeIfAbsent(superClass, k -> new ArrayList<>()).add(cls);
            }
        }

        // Ищем корни (классы без родителей в этом списке)
        for (Class<?> cls : classes) {
            if (cls.getSuperclass() == null || !classes.contains(cls.getSuperclass())) {
                printTree(cls, hierarchy, "", new HashSet<>());
            }
        }
    }

    private static void printTree(Class<?> cls, Map<Class<?>, List<Class<?>>> hierarchy,
                                  String prefix, Set<Class<?>> visited) {
        if (visited.contains(cls)) return;
        visited.add(cls);

        String type = cls.isInterface() ? "(интерфейс)" :
                Modifier.isAbstract(cls.getModifiers()) ? "(абстрактный класс)" :
                        cls.isEnum() ? "(enum)" :
                                cls.isRecord() ? "(record)" : "(класс)";

        System.out.println(prefix + "└── " + cls.getSimpleName() + " " + type);

        List<Class<?>> children = hierarchy.getOrDefault(cls, List.of());
        for (Class<?> child : children) {
            printTree(child, hierarchy, prefix + "    ", visited);
        }
    }
}
