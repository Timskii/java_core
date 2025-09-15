package concurrency.сompletable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Main thread: " + Thread.currentThread().getName());

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Execute other task ... Thread: " + Thread.currentThread().getName());
            try{
//                Thread.sleep(2000);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
            return "Result slow task";
        });

        // Основной поток не заблокирован и может выполнять другую работу
        System.out.println("Main thread: Make other task ...");

        // Получение результата (блокирующий вызов, только для примера)
        String result = future.get();
        System.out.println("result is received: " + result);

        // Запуск задачи, которая не возвращает результат (runAsync)
        CompletableFuture.runAsync(() -> {
            System.out.println("Run a Task without return value. Thread: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task without return value completed.");
        });
    }
}
