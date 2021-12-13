package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay12 {

    Day daySolve = new Day12(false);
    Day dayTest = new Day12(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("226", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("3509", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("4338", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("114189", result);
    }
}
