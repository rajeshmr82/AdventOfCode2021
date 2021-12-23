package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDay22 {

    Day daySolve = new Day22(false);
    Day dayTest = new Day22(true);

    @Test
    void testPart1() {
        String result = dayTest.part1();
        assertEquals("474140", result);
    }

    @Test
    void testPart2() {
        String result = dayTest.part2();
        assertEquals("2758514936282235", result);
    }

    @Test
    void solvePart1() {
        String result = daySolve.part1();
        assertEquals("524792", result);
    }

    @Test
    void solvePart2() {
        String result = daySolve.part2();
        assertEquals("1213461324555691", result);
    }
}
