package exercise;

import java.util.List;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        long count = emails.stream()
                .filter(App::isFree)
                .count();
        return count;
    }

    private static boolean isFree(String email) {
        return email.contains("@gmail.com") || email.contains("@yandex.ru") || email.contains("hotmail.com");
    }
}
// END
