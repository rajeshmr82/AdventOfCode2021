package days;

import java.util.*;
import java.util.stream.Collectors;

public class Day06 extends Day {
    public Day06(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int days = 80;
        int[] ages = new int[9];

        for (String time : input[0].split(",")) {
            ages[Integer.parseInt(time)]++;
        }

        for (int d = 0; d < days; d++) {
            int newChildren = ages[0];
            System.arraycopy(ages, 1, ages, 0, 8);
            ages[6] += newChildren;
            ages[8] = newChildren;
        }

        System.out.printf("Lantern fish count: %d%n", Arrays.stream(ages).sum());
        return String.valueOf(Arrays.stream(ages).sum());
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int days = 256;
        long[] ages = new long[9];

        for (String time : input[0].split(",")) {
            ages[Integer.parseInt(time)]++;
        }

        for (int d = 0; d < days; d++) {
            long newChildren = ages[0];
            System.arraycopy(ages, 1, ages, 0, 8);
            ages[6] += newChildren;
            ages[8] = newChildren;
        }

        System.out.printf("Lantern fish count: %s%n", Arrays.stream(ages).sum());
        return String.valueOf(Arrays.stream(ages).sum());
    }
}
