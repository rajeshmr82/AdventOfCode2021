package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay21 {

    Day daySolve = new Day21(false);
    Day dayTest = new Day21(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("739785", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("444356092776315", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("995904", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("17987", result);
    }
}
