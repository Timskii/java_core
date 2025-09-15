package ss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private static final List<String> data = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        String text = "This is an additional long string for extra memory consumption This is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumptionThis is an additional long string for extra memory consumption";
        for (int j = 0; j < 1000000; j++) {
            data.add(text + j);
            i++;
        }
        Map<String, String> test = new HashMap<>();
        System.out.println(i);
        while (true){
            Thread.sleep(500);

        }
    }
}
