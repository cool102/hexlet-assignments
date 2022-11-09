package exercise.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        // BEGIN
        List<String> companies = getCompanies();
        String sample = request.getParameter("search");
        if (sample != null && sample.length() > 0) {
            List<String> result = companies.stream().filter(c -> c.contains(sample)).toList();
            if (result.isEmpty()) {
               out.println(" Companies not found");
            } else {
                result.forEach(out::println);
            }
        } else {
            companies.forEach(out::println);
        }


        // END
    }
}
