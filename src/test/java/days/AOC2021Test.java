package days;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AOC2021Test {
    Day day;

    @Test
    void testDay1Part1() {
        Day day = new Day1(true);
        String result = day.part1();
        assertEquals("7", result);
    }

    @Test
    void testDay1Part2() {
        Day day = new Day1(true);
        String result = day.part2();
        assertEquals("5", result);
    }
}
