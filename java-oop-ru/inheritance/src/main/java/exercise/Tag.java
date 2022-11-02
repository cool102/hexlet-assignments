package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
class Tag {
    String tagName;
    Map<String, String> attributes;


    public Tag(String tagName,Map<String, String> attributes) {
        this.tagName = tagName;
        Map<String,String> temp = new LinkedHashMap<>(attributes);
        this.attributes = temp;
    }
}

// END
