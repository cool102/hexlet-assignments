package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        final int expectedSize = 2;
        final int countOfElements = 2;
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> actual = App.take(numbers, countOfElements);
        Assertions.assertEquals(actual.size(), expectedSize);
        // END
    }

    @Test
    void testTake1() {
        // BEGIN
        final int expectedSize = 0;
        final int countOfElements = 0;
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> actual = App.take(numbers, countOfElements);
        Assertions.assertEquals(actual.size(), expectedSize);
        // END
    }

    @Test
    void testTake2() {
        // BEGIN
        final int expectedSize = 1;
        final int countOfElements = 1;
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> actual = App.take(numbers, countOfElements);
        Assertions.assertEquals(actual.size(), expectedSize);
        // END
    }

    @Test
    void testTake3() {
        // BEGIN
        final int expectedSize = 8;
        final int countOfElements = 10;
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> actual = App.take(numbers, countOfElements);
        Assertions.assertEquals(actual.size(), expectedSize);
        // END
    }
}
