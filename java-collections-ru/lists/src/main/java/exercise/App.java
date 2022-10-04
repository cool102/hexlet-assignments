package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BEGIN
class App {
    public static void main(String[] args) {
        System.out.println("Hello, Hexlet!");
    }

    public static boolean scrabble(String inputLine, String inputWord) {
        String line = inputLine.toLowerCase();
        String word = inputWord.toLowerCase();
        String[] tmpWordArr = word.split("");
        String[] tmpLineArr = line.split("");
        List<String> wordArr = new ArrayList<>(Arrays.asList(tmpWordArr));
        List<String> lineArr = new ArrayList<>(Arrays.asList(tmpLineArr));

        if (line.length() < word.length()) {
            return false;
        }
        if (!isLineContainsAllCharsWithoutCounting(line, word)) {
            return false;
        } else {
            for (int i = 0; i < wordArr.size(); i++) {
                if (!lineArr.remove(wordArr.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isLineContainsAllCharsWithoutCounting(String line, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!line.contains(String.valueOf(word.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
//END
