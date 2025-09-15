package stream.myStream;

import stream.myStream.impl.SimpleStream;
import stream.myStream.impl.SourceStage;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // Создаём стрим, начиная с SourceStage
        List<String> result = new SourceStage<>(numbers)
                .filter(n -> n % 2 == 0)
                .map(n -> "Number: " + n)
                .collect();

        System.out.println(result); // Вывод: [Number: 2, Number: 4, Number: 6]
    }
}
