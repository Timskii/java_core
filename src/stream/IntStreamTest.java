package stream;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamTest {

    public static void main(String[] args) {

            Set<String> threads = new HashSet<>();

            var integers = IntStream.range(0, 7000).boxed().collect(Collectors.toList());
            integers.stream().parallel().forEach(v -> threads.add(Thread.currentThread().getName()));

            System.out.println(threads.size());

            //System.out.println(threads);
        }

}
