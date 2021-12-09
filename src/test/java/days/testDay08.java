package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay08 {


    Day daySolve = new Day08(false);
    Day08 dayTest = new Day08(true);
    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("26", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("61229", result);
    }

    @Test
    void testPart2A() {
        String result = dayTest.part2A();
        assertEquals("61229", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();

    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("1073431", result);
    }
}
