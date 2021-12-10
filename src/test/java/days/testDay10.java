package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay10 {


    Day daySolve = new Day10(false);
    Day dayTest = new Day10(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("26397", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("288957", result);
    }


    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("240123", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("3260812321", result);
    }
}
