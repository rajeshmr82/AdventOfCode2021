package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay24 {

    Day daySolve = new Day24(false);
    Day dayTest = new Day24(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("99394899891971", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("92171126131911", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("99394899891971", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("92171126131911", result);
    }
}
