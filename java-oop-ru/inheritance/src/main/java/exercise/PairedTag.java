package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// BEGIN
class PairedTag extends Tag {
    private String tagBody;
   private  List<Tag> childrenTags;

    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> childrenTags) {
        super(tagName, attributes);
        this.tagBody = tagBody;
        if (childrenTags != null) {
            this.childrenTags = childrenTags;
        }

    }

    @Override
    public String toString() {
        String tagStart = String.format("<%s ", tagName);
        String tagAttributes = getTagAttributes(attributes);
        String childrenTagsList = getChildrenTagsAsString(childrenTags);

        String tagEnd = String.format("</%s ", tagName);
        StringBuilder result = new StringBuilder();
        result.append(tagStart)
                .append(tagAttributes)
                .append(tagBody)
                .append(childrenTagsList)
                .append(tagEnd);
        return result.toString();
    }

    private static String getChildrenTagsAsString(List<Tag> childrenTags) {
        String childrenTagsAsString = childrenTags.toString();

        String withoutBrackets = childrenTagsAsString.substring(1, childrenTagsAsString.length() - 1);
        return withoutBrackets.replace(", ","");

    }

    private String getTagAttributes(Map<String, String> attributes) {
        StringBuilder tagAttributes = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String attribute = entry.getKey();
            String value = entry.getValue();
            tagAttributes.append(getPairOfAttributeAndValue(attribute, value));
            tagAttributes.append(" ");
        }
        return tagAttributes.append(">").deleteCharAt(tagAttributes.length() - 2).toString();

    }

    private static final String getPairOfAttributeAndValue(String attribute, String value) {
        return attribute + "=" + "\"" + value + "\"";
    }
}
// END
