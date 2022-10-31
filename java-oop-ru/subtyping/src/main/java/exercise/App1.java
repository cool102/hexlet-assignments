package exercise;

import java.util.Map;

public class App1 {
    public static void main(String[] args) {
        KeyValueStorage db = new InMemoryKV(Map.of("key", "value", "key2", "value2"));
        App.swapKeyValue(db);
    }
}
