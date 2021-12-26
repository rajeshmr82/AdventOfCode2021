package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay23 {

    Day daySolve = new Day23(false);
    Day dayTest = new Day23(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("12521", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("44169", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("16506", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("48304", result);
    }
}
