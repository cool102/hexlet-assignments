package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {
    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName, attributes);
    }

    @Override
    public String toString() {
        String tagStart = String.format("<%s", tagName);
        StringBuilder result = new StringBuilder(tagStart);
        result.append(" ");
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String attribute = entry.getKey();
            String value = entry.getValue();
            result.append(getPairOfAttributeAndValue(attribute, value));
            result.append(" ");
        }
        result.append(">").deleteCharAt(result.length() - 2);
        return result.toString();
    }

    private static final String getPairOfAttributeAndValue(String attribute, String value) {
        return attribute + "=" + "\"" + value + "\"";
    }
}
// END
