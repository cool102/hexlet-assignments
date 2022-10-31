package exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage db) {
        Iterator<Map.Entry<String, String>> iterator = db.toMap().entrySet().iterator();

        Map<String, String> temp = new HashMap<>();

        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();

            String key = next.getKey();
            String value = next.getValue();

            temp.put(value,key);
            db.unset(key);
        }


        Iterator<Map.Entry<String, String>> tempIterator = temp.entrySet().iterator();

        while (tempIterator.hasNext()) {
            Map.Entry<String, String> next = tempIterator.next();
            String key = next.getKey();
            String value = next.getValue();
            db.set(key,value);
        }
        System.out.println();


    }
}
// END
