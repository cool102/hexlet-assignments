package exercise.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path path = Paths.get("src/main/resources/users.json");
        String jsonArrayUsers = Files.readString(path);
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(jsonArrayUsers, new TypeReference<List<User>>() {
        });
        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        // BEGIN
        List<User> users = getUsers();
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<title>Example application | Users</title>" +
                "<meta charset=\"utf-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\n" +
                "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>" +
                "</head>" +
                "<body>" +
                "<table class=\"table table-bordered\">" +
                "<thead>" +
                "<tr>" +
                "<th>ID</th>" +
                "<th>Full name</th>" +
                "</tr>" +
                "</thead><tbody>");
        for (User current : users) {
            String id = current.getId();
            String firstName = current.getFirstName();
            String lastName = current.getLastName();
            String name = firstName + " " + lastName;
            sb.append("<tr><th>" + id + "</th>");
            sb.append("<td><a href=/users/" + id + ">" + name + "<a></td></tr>");
        }
        sb.append("</tbody></table>");
        sb.append("</body></html>");
        String result = sb.toString();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(result);
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        List<User> users = getUsers();
        User find = null;
        for (User cur : users) {
            if (cur.getId().equals(id)) {
                find = cur;
                break;
            }

        }
        if (find != null) {
            String id1 = find.getId();
            String firstName = find.getFirstName();
            String lastName = find.getLastName();
            String email = find.getEmail();
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>" +
                            "<html lang=\"en\">" +
                            "<head>" +
                            "<title>Bootstrap Example</title>\n" +
                            "  <meta charset=\"utf-8\">\n" +
                            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                            "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" +
                            "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\n" +
                            "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>" +
                    "</head>" +
                            "<body>" +
                            "<div class=\"container\">\n" +
                            "  <h2>Found user</h2>\n" +

                            "  <table class=\"table table-bordered\">\n" +
                            "    <thead>\n" +
                            "      <tr>\n" +
                            "        <th>Parameter</th>\n" +
                            "        <th>Value</th>\n" +

                            "      </tr>\n" +
                            "    </thead>\n" +
                            "    <tbody>\n" +

                            "      <tr>\n" +
                            "        <td>Identificator</td>\n" +
                            "        <td>" + id1 + "</td>\n" +
                            "      </tr>\n" +
                            "      <tr>\n" +
                            "        <td>First name</td>\n" +
                            "        <td>" + firstName + "</td>\n" +
                            "      </tr>\n" +
                            "      <tr>\n" +
                            "        <td>Last name</td>\n" +
                            "        <td>" + lastName + "</td>\n" +
                            "      </tr>\n" +
                            "<tr>\n" +
                            "<td>Email</td>\n" +
                            "        <td>" + email + "</td>\n" +
                            "      </tr>\n" +
                            "    </tbody>\n" +
                            "  </table>\n" +
                            "</div>" +
                            "</body>\n" +
                            "</html>");


            String result = sb.toString();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(result);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }


        // END
    }
}
