package days;

import java.util.LinkedList;
import java.util.Queue;

public class Day1 extends Day {

    public Day1(Boolean isTest) {
        super(isTest);
        System.out.println(getClass().toString());
    }

    public String part1() {
        String input = this.getInput();
        int prev = -1;
        int largerThanPrev = 0;
        for (String line : input.split("\r\n")) {
            if (line.isEmpty()) break;

            int num = Integer.parseInt(line);
            if (prev != -1 && num > prev) {
                largerThanPrev++;
            }
            prev = num;
        }

        return String.valueOf(largerThanPrev);
    }

    public String part2() {
        String input = this.getInput();
        Queue<Integer> queue = new LinkedList<>();

        int largerThanPrev = 0, sum = 0, prevSum = 0;
        for (String line : input.split("\r\n")) {
            int num = Integer.parseInt(line);
            queue.add(num);
            sum += num;
            if (queue.size() == 3) {
                if (prevSum > 0 && sum > prevSum) {
                    largerThanPrev++;
                }
                prevSum = sum;
                int oldest = queue.poll();
                sum -= oldest;
            }
        }

        System.out.printf("measurements are larger than the previous measurement: %d%n", largerThanPrev);
        return String.valueOf(largerThanPrev);
    }
}
