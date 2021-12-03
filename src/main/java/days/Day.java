package days;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public abstract class Day {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Day(Boolean isTest) {
        inputFile = getClass().getSimpleName().toLowerCase() + (isTest ? ".test" : ".input");
    }

    public String inputFile;
    public String getInput() {
        try {
            File f = new File(Objects.requireNonNull(Day.class.getClassLoader().getResource(inputFile)).getFile());
            if (!f.exists()) {
                System.out.printf("%s not found%n", this.inputFile);
                return null;
            }
            Scanner scanner = new Scanner(f);
            return scanner.useDelimiter("\\Z").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract String part1();
    public abstract String part2();
    public void run() {
        System.out.printf("%sRunning %s%s%n", ANSI_GREEN, this.getClass().getName(), ANSI_RESET);

        Instant start1 = Instant.now();
        String part1Result = this.part1();
        System.out.printf("Result for Part 1: %s%n", part1Result);
        Instant end1 = Instant.now();
        long runtimePart1 = Duration.between(start1, end1).toMillis();
        System.out.printf("Part 1 took %d ms%n", runtimePart1);

        Instant start2 = Instant.now();
        String part2Result = this.part2();
        System.out.printf("Result for Part 2: %s%n", part2Result);
        Instant end2 = Instant.now();
        long runtimePart2 = Duration.between(start2, end2).toMillis();
        System.out.printf("Part 2 took %d ms%n", runtimePart2);
        System.out.printf("\n%s%n", ANSI_RESET);
    }
}
