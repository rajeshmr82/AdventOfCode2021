package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay15 {

    Day daySolve = new Day15(false);
    Day dayTest = new Day15(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("40", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("315", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("581", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("2916", result);
    }
}
