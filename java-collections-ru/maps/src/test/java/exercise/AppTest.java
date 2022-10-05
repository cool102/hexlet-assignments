package exercise;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    void testGetWordsCount() {
        final int expectedWord = 3;
        final int expectedApple = 2;
        final int expectedText = 1;
        final int expectedDog = 1;
        String sentence1 = "word text dog apple word apple word";
        Map<String, Integer> actual1 = App.getWordCount(sentence1);
        Map<String, Integer> expected1 = new HashMap<>();
        expected1.put("word", expectedWord);
        expected1.put("apple", expectedApple);
        expected1.put("text", expectedText);
        expected1.put("dog", expectedDog);
        assertThat(actual1).containsExactlyInAnyOrderEntriesOf(expected1);

        String sentence2 = "";
        Map<String, Integer> actual2 = App.getWordCount(sentence2);
        assertThat(actual2).isEmpty();

    }

    @Test
    void testToString() {
        String sentence1 = "word text cat apple word map apple word";
        final int intExpectedSize1 = 52;
        final int intExpectedSize2 = 43;
        Map<String, Integer> wordCount1 = App.getWordCount(sentence1);
        String actual1 = App.toString(wordCount1).trim();
        String[] expectedSubstrings1 = {"  apple: 2\n", "  cat: 1\n", "  text: 1\n", "  word: 3\n", "  map: 1\n"};
        assertThat(actual1).contains(expectedSubstrings1);
        assertThat(actual1).hasSize(intExpectedSize1);

        String sentence2 = "word text cat apple word apple word";
        Map<String, Integer> wordCount2 = App.getWordCount(sentence2);
        String actual2 = App.toString(wordCount2).trim();
        String[] expectedSubstrings2 = {"  apple: 2\n", "  cat: 1\n", "  text: 1\n", "  word: 3\n"};
        assertThat(actual2).contains(expectedSubstrings2);
        assertThat(actual2).hasSize(intExpectedSize2);

        String sentence3 = "";
        Map<String, Integer> wordCount3 = App.getWordCount(sentence3);
        String actual3 = App.toString(wordCount3);
        assertThat(actual3.trim()).isEqualTo("{}");
    }
}
