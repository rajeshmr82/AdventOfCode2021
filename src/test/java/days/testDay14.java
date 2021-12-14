package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay14 {

    Day daySolve = new Day14(false);
    Day dayTest = new Day14(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("1588", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("2188189693529", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("2988", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("3572761917024", result);
    }
}
