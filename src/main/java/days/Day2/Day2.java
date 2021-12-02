package days.Day2;

import days.Day;

import java.util.LinkedList;
import java.util.Queue;

public class Day2 extends Day {
    public Day2(){
        inputFilePart1 = "src/main/java/days/Day2/input1.txt";
        inputFilePart2 = "src/main/java/days/Day2/input1.txt";
    }

    public void part1(){
        String input= this.getInputPart1();

        int horizontal=0;
        int depth=0;
        for (String command : input.split("\r\n")) {
            String[] instructions = command.split(" ");
            String dir = instructions[0];
            int units = Integer.parseInt(instructions[1]);
            switch (dir) {
                case "forward":
                    horizontal += units;
                    break;
                case "down":
                    depth += units;
                    break;
                case "up":
                    depth -= units;
                    break;
            }
        }
        System.out.printf("final horizontal position by final depth: %d%n", horizontal*depth);
    }

    public void part2() {
        String input= this.getInputPart2();

        int horizontal=0;
        int depth=0;
        int aim=0;
        for (String command : input.split("\r\n")) {
            String[] instructions = command.split(" ");
            String dir = instructions[0];
            int units = Integer.parseInt(instructions[1]);
            switch (dir) {
                case "forward":
                    horizontal += units;
                    depth += aim * units;
                    break;
                case "down":
                    aim += units;
                    break;
                case "up":
                    aim -= units;
                    break;
            }
        }
        System.out.printf("final horizontal position by final depth: %d%n", horizontal*depth);
    }

}
