package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static String getForwardedVariables(String configuration) {

        String[] blocks = configuration.split("\n");

        String result = Arrays.stream(blocks)
                .filter(b -> b.contains("X_FORWARDED_"))
                .map(b -> b.replaceAll("\"", ""))
                .map(String::trim)
                .filter(b -> b.startsWith("environment"))
                .map(b -> b.replaceAll("environment=", ""))
                .collect(Collectors.joining(","));
        if (result.charAt(result.length() - 1) == ',') {
            result = result.substring(0, result.length() - 1);
        }
        String result2 = Arrays.stream(result.split(","))
                .filter(s -> s.contains("X_FORWARDED_"))
                .map(s -> s.replace("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
        return result2;

    }
}
//END
