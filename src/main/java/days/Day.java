package days;
import java.io.*;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public abstract class Day {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public String inputFilePart1;
    public String inputFilePart2;
    public String getInputPart1() {
        try {
            File f = new File(this.inputFilePart1);
            if (!f.exists()) {
                System.out.printf("%s not found%n", this.inputFilePart1);
                return null;
            }
            Scanner scanner = new Scanner(f);
            return scanner.useDelimiter("\\Z").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getInputPart2() {
        try {
            File f = new File(this.inputFilePart2);
            if (!f.exists()) {
                System.out.printf("%s not found%n", this.inputFilePart2);
                return null;
            }
            Scanner scanner = new Scanner(f);
            return scanner.useDelimiter("\\Z").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public abstract void part1();
    public abstract void part2();
    public void run() {
        System.out.printf("%sRunning %s%s%n", ANSI_GREEN, this.getClass().getName(), ANSI_RESET);

        Instant start1 = Instant.now();
        this.part1();
        Instant end1 = Instant.now();
        long runtimePart1 = Duration.between(start1, end1).toMillis();
        System.out.printf("Part 1 took %d ms%n", runtimePart1);

        Instant start2 = Instant.now();
        this.part2();
        Instant end2 = Instant.now();
        long runtimePart2 = Duration.between(start2, end2).toMillis();
        System.out.printf("Part 2 took %d ms%n", runtimePart2);
        System.out.printf("\n%s%n", ANSI_RESET);
    }
}
