package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book : books) {
            Set<Map.Entry<String, String>> collection = map.entrySet();
            Set<Map.Entry<String, String>> entries = book.entrySet();
            if (entries.containsAll(collection)) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
