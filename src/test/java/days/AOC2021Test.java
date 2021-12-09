package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AOC2021Test {

    @Test
    void testDay1Part1() {
        Day day = new Day01(true);
        String result = day.part1();
        assertEquals("7", result);
    }

    @Test
    void testDay1Part2() {
        Day day = new Day01(true);
        String result = day.part2();
        assertEquals("5", result);
    }

    @Test
    void testDay4Part1() {
        Day day = new Day04(true);
        String result = day.part1();
        assertEquals("4512", result);
    }

    @Test
    void testDay4Part2() {
        Day day = new Day04(true);
        String result = day.part2();
        assertEquals("1924", result);
    }

    @Test
    void testDay5Part1() {
        Day day = new Day05(true);
        String result = day.part1();
        assertEquals("5", result);
    }

    @Test
    void testDay5Part2() {
        Day day = new Day05(true);
        String result = day.part2();
        assertEquals("12", result);
    }

    @Test
    void testDay6Part1() {
        Day day = new Day06(true);
        String result = day.part1();
        assertEquals("5934", result);
    }
    @Test
    void testDay6Part2() {
        Day day = new Day06(true);
        String result = day.part2();
        assertEquals("26984457539", result);
    }

    @Test
    void testDay7Part1() {
        Day day = new Day07(true);
        String result = day.part1();
        assertEquals("37", result);
    }
    @Test
    void testDay7Part2() {
        Day day = new Day07(true);
        String result = day.part2();
        assertEquals("168", result);
    }
}
