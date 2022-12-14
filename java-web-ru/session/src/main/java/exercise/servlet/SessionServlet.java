package exercise.servlet;

import exercise.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static exercise.App.getUsers;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        switch (request.getRequestURI()) {
            case "/login" -> login(request, response);
            case "/logout" -> logout(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        HttpSession session = request.getSession();
        String inputEmail = request.getParameter("email");
        String inputPassword = request.getParameter("password");
        Map<String, String> inSystem = users.findByEmail(inputEmail);
        if (inSystem != null & inputPassword.equals("password")) {
            session.setAttribute("userId", inSystem.get("id"));
            session.setAttribute("flash", "???? ?????????????? ??????????");
            response.sendRedirect("/");
        } else {
            session.setAttribute("flash", "???????????????? ?????????? ?????? ????????????");
            response.setStatus(422);
            request.setAttribute("email",inputEmail);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        }
        // END
    }

    private void logout(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        // BEGIN
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        session.setAttribute("flash","???? ?????????????? ??????????");
        response.sendRedirect("/");
        // END
    }


}
