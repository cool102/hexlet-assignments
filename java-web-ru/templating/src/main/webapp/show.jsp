<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Map" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>All users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
    <%
        List<Map<String,String>> users = (List<Map<String,String>>) request.getAttribute("users");
    %>
    <%
        String id = (String) request.getAttribute("id");
        String name = null;
        String email = null;

        for (Map<String,String> user : users) {
            if ( user.get("id").equals(id) ) {
            String f = user.get("firstName");
            String l = user.get("lastName");
            String e = user.get("email");
            name = f + " " + l;
            email = e;
            }
        }
    %>


        <div class="container">
          <h2>Founded user</h2>
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>Identificator</th>
                <th>Name</th>
                <th>Email</th>
                <th>Options</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><%= id%></td>
                <td><%= name%></td>
                <td><%= email%></td>
                <td><a href='/users/delete?id=${id}'> Delete user</a></td>
              </tr>
            </tbody>
          </table>
        </div>

    </body>
</html>
