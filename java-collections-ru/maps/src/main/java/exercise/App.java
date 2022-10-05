package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {

    public static String toString(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        if (map.isEmpty()) {
            return "{}";
        }
        sb.append("{\n");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append("  ");
            sb.append(entry.getKey() + ": ");
            sb.append(entry.getValue() + "\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public static Map<String, Integer> getWordCount(String line) {
        if (line.isEmpty()) {
            return Map.of();
        }
        Map<String, Integer> map = new HashMap<>();
        int value = 0;
        String[] words = line.split(" ");
        for (String key : words) {
            value = wordCounter(words, key);
            map.put(key, value);
        }
        return map;
    }

    public static int wordCounter(String[] arr, String word) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(word)) {
                counter++;
            }
        }
        return counter;

    }

}
//END
