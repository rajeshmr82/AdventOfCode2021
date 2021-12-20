package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay18 {

    Day daySolve = new Day18(false);
    Day dayTest = new Day18(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("4140", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("3993", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("4243", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("4701", result);
    }
}
