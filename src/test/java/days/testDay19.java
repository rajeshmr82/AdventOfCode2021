package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay19 {

    Day daySolve = new Day19(false);
    Day dayTest = new Day19(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("79", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("3621", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("454", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("10813", result);
    }
}
