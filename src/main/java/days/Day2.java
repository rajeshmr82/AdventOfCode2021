package days;

public class Day2 extends Day {
    public Day2(Boolean isTest){
        super(isTest);
    }

    public String part1(){
        String input = this.getInput();

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

        return String.valueOf(horizontal*depth);
    }

    public String part2() {
        String input = this.getInput();

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
        return String.valueOf(horizontal*depth);
    }

}
