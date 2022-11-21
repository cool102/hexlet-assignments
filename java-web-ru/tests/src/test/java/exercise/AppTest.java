package exercise;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.DB;
import io.ebean.Database;
import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void start() {

        app = App.getApp();
        app.start();
        int port = app.port();
        baseUrl = "http://localhost:" + port;

    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void createUserWithBadPassword() {
        String firstName = "Roi";
        String lastName = "Atkins";
        String email = "Atkins@gmail.com";
        String password = "pas";
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);

    }
    @Test
    void createUserWithBadName() {
        String firstName = "";
        String lastName = "Atkins";
        String email = "Atkins@gmail.com";
        String password = "pas";
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        assertThat(response.getBody()).contains("Name can not be empty");
    }
    @Test
    void createUserWithBadLastName() {
        String firstName = "Roi";
        String lastName = "Atkins";
        String email = "Atkins@gmail.com";
        String password = "pas";
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        assertThat(response.getBody()).contains("password must be unless 4 symbols");

    }
    @Test
    void createUserWithBadEmail() {
        String firstName = "Roi";
        String lastName = "Atkins";
        String email = "Atkinsgmail.com";
        String password = "pas";
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        assertThat(response.getBody()).contains("email must be valid");
    }

    @Test
    void createUser() {
        String firstName = "Roi";
        String lastName = "Atkins";
        String email = "Atkins@gmail.com";
        String password = "password";
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();
        assertThat(response.getStatus()).isEqualTo(302);

        User fromDb = new QUser().lastName.equalTo("Atkins").findOne();
        assertThat(fromDb.getFirstName().equals(firstName));
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users")
                .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users/new")
                .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @AfterAll
    public static void stop() {
        app.stop();
    }
    // END
}
