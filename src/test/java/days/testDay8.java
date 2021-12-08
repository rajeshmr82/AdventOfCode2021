package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay8 {


    Day daySolve = new Day8(false);

    @Test
    void testPart1() {
        Day dayTest = new Day8(true);
        String result = dayTest.part1();
        assertEquals("26", result);
    }

    @Test
    void testPart2() {
        Day dayTest = new Day8(true);
        String result = dayTest.part2();
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
