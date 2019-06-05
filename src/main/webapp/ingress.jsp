<%--
  Created by IntelliJ IDEA.
  User: Мария
  Date: 29.05.2019
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход или регистрация</title>
    <link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header class="flexbody banner">
    <div class="flexheader logo">IndexBot</div>
    <div class="flexheader description">Приложение для анализа содержимого сайта</div>
</header>
<main class="flexbody purport">
    <div class="flexmain image"><img src="Попугай.png" alt="Привет, пользователь!" type="image/png">
    </div>
    <div class="flexmain interface">
        <form id="fin" action="ingressApp.js">
            <p><label for="login" class="login_field">Логин:</label></p>
            <p><input id="login" type="text" size="30"></p>
            <p><label for="password" class="password_field">Пароль:</label></p>
            <p><input id="password" type="password" size="30"></p>
            <div class="password2" id="psw2">
                <p><label for="new_password" class="new_password_field" id="npf"></label></p>
                <p><input id="new_password" type="password" size="30"></p>
            </div>
            <p><input class="but ingress" id="igr" type="button" value="Войти">
                <input class="but cancel" id="cl" type="reset" value="Отмена"></p>
            <p><a href="#new_password" id="registration">Зарегистрироваться</a>
                <a href="#new_password" id="change_password">Изменить пароль</a></p>
        </form>
    </div>
</main>
<footer class="flexbody acquaintance">
    <div class="flexfooter tie">
        <div class="developer">Разработка и поддержка: Мария Отинова</div>
        <div class="Contact_details">Контактные данные: mariya-otinova@yandex.ru</div>
    </div>
</footer>
<script src="ingressApp.js" type="text/javascript" charset="utf-8" defer></script>
<script src="http://code.jquery.com/jquery-3.4.1.js" type="text/javascript"></script>
</body>
</html>
