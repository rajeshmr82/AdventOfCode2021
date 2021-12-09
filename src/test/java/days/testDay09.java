package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay09 {


    Day daySolve = new Day09(false);
    Day dayTest = new Day09(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("15", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("1134", result);
    }


    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("500", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("970200", result);
    }
}
