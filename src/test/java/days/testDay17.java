package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay17 {

    Day daySolve = new Day17(false);
    Day dayTest = new Day17(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("45", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("112", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("10011", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("2994", result);
    }
}
