package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


class AppTest {

    @Test
    void testSingleTag() {
        Map<String, String> attributes1 = new LinkedHashMap<>();
        attributes1.put("class", "w-75");
        attributes1.put("id", "wop");
        Tag img = new SingleTag("img", attributes1);
        String actual1 = img.toString();
        String expected1 = "<img class=\"w-75\" id=\"wop\">";
        Assertions.assertEquals(expected1, actual1);

        Map<String, String> attributes2 = new LinkedHashMap<>();
        Tag hr = new SingleTag("hr", attributes2);
        String actual2 = hr.toString();
        String expected2 = "<hr>";
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void testPairedTag() {
        Map<String, String> attributes1 = new LinkedHashMap<>();
        attributes1.put("class", "m-10");
        attributes1.put("id", "10");
        attributes1.put("lang", "en");

        // List<Tag> children = new ArrayList<>();

        Tag p = new PairedTag("p", attributes1, "Text paragraph", new ArrayList<Tag>());
        String actual1 = p.toString();
        String expected1 = "<p class=\"m-10\" id=\"10\" lang=\"en\">Text paragraph</p>";
        Assertions.assertEquals(expected1, actual1);

        Map<String, String> attributes2 = new LinkedHashMap<>();
        Tag span = new PairedTag("span", attributes2, "", new ArrayList<Tag>());
        String actual2 = span.toString();
        String expected2 = "<span></span>";
        Assertions.assertEquals(expected2, actual2);

    }

    @Test
    void testPairedTagWithChildren() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("lang", "ru");
        attributes.put("id", "abc");


        List<Tag> children = List.of(
                new SingleTag("br", Map.of("id", "s")),
                new SingleTag("hr", Map.of("class", "a-5"))
        );

        Tag div = new PairedTag("div", attributes, "", children);
        String actual = div.toString();
        String expected = "<div lang=\"ru\" id=\"abc\"><br id=\"s\"><hr class=\"a-5\"></div>";
        Assertions.assertEquals(actual, expected);
    }
}
