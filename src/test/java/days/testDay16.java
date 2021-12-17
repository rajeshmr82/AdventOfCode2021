package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay16 {

    Day daySolve = new Day16(false);
    Day dayTest = new Day16(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("20", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("1", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("947", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("660797830937", result);
    }
}
