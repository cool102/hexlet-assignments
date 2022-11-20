package exercise.servlet;

import exercise.TemplateEngineUtil;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                try {
                    showArticles(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    showArticle(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN

        String page = request.getParameter("page");
        String offset = null;
        int pageAsInt = 0;
        if (page == null || page.equals("1")) {
            offset = "0";
        } else {
            pageAsInt = Integer.parseInt(page);
            offset = String.valueOf(pageAsInt * 10 - 10);
        }

        String sql = "SELECT * FROM articles ORDER BY id LIMIT 10 OFFSET ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, offset);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Map<String, String>> articles = new ArrayList<Map<String, String>>();

        while (resultSet.next()) {
            Map<String, String> article = new HashMap<String, String>();
            article.put("title", resultSet.getString(2));
            article.put("body", resultSet.getString(3));
            articles.add(article);
        }
        /*resultSet.close();
        preparedStatement.close();
        connection.close();*/


        request.setAttribute("page", pageAsInt);
        request.setAttribute("articles", articles);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = request.getParameter("id");
        String sql = "SELECT * FROM articles WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        resultSet.first();
        String title = resultSet.getString(2);
        String body = resultSet.getString(3);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        request.setAttribute("title", title);
        request.setAttribute("body", body);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
