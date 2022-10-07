package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

// BEGIN
class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        LinkedHashMap<String, String> res = new LinkedHashMap<>();

        TreeMap<String, Object> map1 = new TreeMap<String, Object>();
        map1.putAll(data1);
        TreeMap<String, Object> map2 = new TreeMap<String, Object>();
        map2.putAll(data2);

        for (String current : map1.keySet()) {
            if (map2.keySet().contains(current)) {
                Object o = map2.get(current);
                if (o.equals(map1.get(current))) {
                    res.put(current, "unchanged");
                } else {
                    res.put(current, "changed");
                }
            } else {
                res.put(current, "deleted");
            }
        }

        for (String current : map2.keySet()) {
            if (!map1.keySet().contains(current)) {
                res.put(current, "added");
            }
        }
        return res;
    }
}
//END
