package days.Day1;

import days.Day;

import java.util.LinkedList;
import java.util.Queue;

public class Day1 extends Day {
    public Day1(){
        inputFilePart1 = "src/main/java/days/Day1/input1.txt";
        inputFilePart2 = "src/main/java/days/Day1/input2.txt";
    }

    public void part1(){
        String input= this.getInputPart1();
        int prev = -1;
        int largerThanPrev=0;
        for (String line : input.split("\r\n")) {
            if(line.isEmpty()) break;

            int num = Integer.parseInt(line);
            if(prev!=-1 && num>prev){
                largerThanPrev++;
            }
            prev=num;
        }
        System.out.printf("measurements are larger than the previous measurement: %d%n", largerThanPrev);
    }

    public void part2() {
        String input = this.getInputPart2();
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
    }

}
