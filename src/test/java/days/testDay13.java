package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay13 {

    Day daySolve = new Day13(false);
    Day dayTest = new Day13(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("17", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("16", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("763", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("103", result);
    }
}
