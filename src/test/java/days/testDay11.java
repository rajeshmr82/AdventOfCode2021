package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay11 {

    Day daySolve = new Day11(false);
    Day dayTest = new Day11(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("1656", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("195", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("1665", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("235", result);
    }
}
