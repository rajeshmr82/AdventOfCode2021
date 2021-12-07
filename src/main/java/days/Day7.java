package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day7 extends Day {
    public Day7(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int[] positions = Arrays.stream(input[0].split(",")).mapToInt(Integer::parseInt).toArray();
        int min =Arrays.stream(positions).min().getAsInt();
        int max = Arrays.stream(positions).max().getAsInt();
        int minCost = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int pos =i;
            int cost = Arrays.stream(positions).reduce(0, (acc, ele) -> acc + Math.abs(ele - pos));

            if (cost < minCost) {
                minCost = cost;
            }
        }

        System.out.printf("Fuel Required: %d%n", minCost);
        return String.valueOf(minCost);
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int[] positions = Arrays.stream(input[0].split(",")).mapToInt(Integer::parseInt).toArray();
        int min =Arrays.stream(positions).min().getAsInt();
        int max = Arrays.stream(positions).max().getAsInt();
        int minCost = Integer.MAX_VALUE;
        for (int i = min; i <=max ; i++) {
            int pos =i;
            int cost = Arrays.stream(positions).map(p -> Math.abs(p- pos)).reduce(0, (acc, dist) -> acc+ (dist * (dist+1)/2));
            if (cost < minCost) {
                minCost = cost;
            }
        }
        System.out.printf("Fuel Required: %d%n", minCost);
        return String.valueOf(minCost);
    }
}
