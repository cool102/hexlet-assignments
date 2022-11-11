package exercise.servlet;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static exercise.Data.getNextId;
import static exercise.Data.getUsers;

public class UsersServlet extends HttpServlet {

    private List<Map<String, String>> users = getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }

    private Map<String, String> getUserById(String id) {
        Map<String, String> user = users
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);

        return user;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showUsers(request, response);
                break;
            case "new":
                newUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "show":
                showUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "new":
                createUser(request, response);
                break;
            case "edit":
                updateUser(request, response);
                break;
            case "delete":
                destroyUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
    }


    private void showUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(request, response);
    }

    private void newUser(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        Map<String, String> emptyUser = new HashMap<>(Map.of("firstName", "", "lastName", "", "email", ""));
        request.setAttribute("user", emptyUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");

        requestDispatcher.forward(request, response);
        // END
    }

    private void createUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String id = getNextId();
        Map<String, String> user = new HashMap<String, String>();
        if (firstName.isEmpty() || firstName == null || lastName.isEmpty() || lastName == null) {
            //response.sendError(422, "Unprocessable Entity");
            response.setStatus(422);
            request.setAttribute("error", "Unprocessable Entity");
            Map<String, String> badUser = new HashMap<>(Map.of("firstName", firstName, "lastName", lastName, "email", email));
            request.setAttribute("user", badUser);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
            requestDispatcher.forward(request, response);
        } else {
            user.put("firstName", firstName);
            user.put("lastName", lastName);
            user.put("email", email);
            user.put("id", id);
            users.add(user);
            response.sendRedirect("/users");
        }

    }

    private void editUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);
        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(request, response);
       // response.sendRedirect("/edit.jsp");
        // END
    }

    private void updateUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);
        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String newFirstName = request.getParameter("firstName");
        String newLastName = request.getParameter("lastName");
        String newEmail = request.getParameter("email");


        if (newFirstName.isEmpty() || newFirstName == null || newLastName.isEmpty() || newLastName == null) {
            //response.sendError(422, "Unprocessable Entity");
            response.setStatus(422);
            request.setAttribute("error", "Unprocessable Entity");
            Map<String, String> badCredentials = new HashMap<>(Map.of("firstName", newFirstName, "lastName", newLastName, "email", newEmail,"id", id));
            request.setAttribute("user", badCredentials);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
            requestDispatcher.forward(request, response);
        } else {
            user.put("firstName", newFirstName);
            user.put("lastName", newLastName);
            user.put("email", newEmail);
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
            requestDispatcher.forward(request, response);
            //response.sendRedirect("/show.jsp");
        }
        // END
    }

    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(request, response);

    }

    private void destroyUser(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        users.remove(user);
        response.sendRedirect("/users");
    }
}
