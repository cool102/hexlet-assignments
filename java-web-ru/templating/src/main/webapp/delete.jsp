<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Deleting page</title>
        <!-- Подключаем стили Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
       <!-- // В цикле выводим всех пользователей -->
       <!-- // Внутри jsp-файла нам доступна переменная users, которую мы установили в сервлете -->
       <h4>Are you sure that you want delete user ${user} ?</h4>
        <!--//кнопка -->
        <form action="/users/delete?id=${user.get("id")}" method="post">
            <button type="submit" class="btn btn-danger">Удалить</button>
        </form>
    </body>
</html>