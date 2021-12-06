package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AOC2021Test {

    @Test
    void testDay1Part1() {
        Day day = new Day1(true);
        String result = day.part1();
        assertEquals("7", result);
    }

    @Test
    void testDay1Part2() {
        Day day = new Day1(true);
        String result = day.part2();
        assertEquals("5", result);
    }

    @Test
    void testDay4Part1() {
        Day day = new Day4(true);
        String result = day.part1();
        assertEquals("4512", result);
    }

    @Test
    void testDay4Part2() {
        Day day = new Day4(true);
        String result = day.part2();
        assertEquals("1924", result);
    }

    @Test
    void testDay5Part1() {
        Day day = new Day5(true);
        String result = day.part1();
        assertEquals("5", result);
    }

    @Test
    void testDay5Part2() {
        Day day = new Day5(true);
        String result = day.part2();
        assertEquals("12", result);
    }
}
