package exercise;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    // @Test
// void testMain() throws Exception {
//     String result = tapSystemOut(() -> {
//         App.main(null);
//     });
//     // здесь происходит сравнение ожидаемой строки с пришедшей
//     assertThat(result.trim()).isEqualTo("This is Hexlet!");
// }
    @Disabled
    @Test
    void isLineContainsAllCharsWithoutCounting1() throws Exception {
        boolean result = App.scrabble("jvx", "java");
        assertThat(result).isFalse();
    }

    @Disabled
    @Test
    void isLineContainsAllCharsWithoutCounting2() throws Exception {
        boolean result = App.scrabble("jva", "java");
        assertThat(result).isTrue();
    }

    @Disabled
    @Test
    void testSrabble11() throws Exception {
        boolean result = App.scrabble("avbj", "java");
        assertThat(result).isFalse();
    }
    @Disabled
    @Test
    void testSrabble12() throws Exception {
        boolean result = App.scrabble("avbja", "java");
        assertThat(result).isTrue();
    }

    @Test
    void testSrabble1() throws Exception {
        boolean result = App.scrabble("rkqodlw", "woRld");
        assertThat(result).isTrue();
    }

    @Test
    void testSrabble2() throws Exception {
        boolean result = App.scrabble("begsdhhtsexoult", "Hexlet");
        assertThat(result).isTrue();
    }

    @Test
    void testSrabble3() throws Exception {
        boolean result = App.scrabble("thlxertwq", "hexlet");
        assertThat(result).isFalse();
    }

    @Test
    void testSrabble4() throws Exception {
        boolean result = App.scrabble("jvayu", "java");
        assertThat(result).isFalse();
    }

    @Test
    void testSrabble5() throws Exception {
        boolean result = App.scrabble("", "java");
        assertThat(result).isFalse();
    }


}
