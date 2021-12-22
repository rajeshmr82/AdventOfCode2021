package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay20 {

    Day daySolve = new Day20(false);
    Day dayTest = new Day20(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("35", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("3351", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("5097", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("17987", result);
    }
}
