<%--
  Created by IntelliJ IDEA.
  User: Мария
  Date: 29.05.2019
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Статистика</title>
    <link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header class="flexbody banner">
    <div class="flexheader logo">IndexBot</div>
    <div class="flexheader out"><button class="but go_out" id="go_out">Выйти</button></div>
</header>
<form id="finn" action="statisticApp.js">
    <main class="flexbody infostat">
        <div class="flexmain table">
            <table id="tablesite">
                <thead>
                <tr>
                    <th>Адрес сайта</th>
                    <th>Дата создания</th>
                    <th>Дата индексации</th>
                    <th>Колич. уник. слов</th>
                    <th>Колич. слов</th>
                    <th>Колич. слов на лат.</th>
                    <th>Колич. слов на кирил.</th>
                </tr>
                </thead>
                <tbody id="tb">
                </tbody>
                <tfoot id="tf">
                <tr><td><input id="addurl" type="text" size="20" class="in" name="addurl"></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="flexmain histograms">
            <h1 class="histname">Частота использования <a href="#hw" id="showhw">слов</a><span> и</span>
                <a href="#hl" id="showhl">букв</a>.</h1>
            <div class="hist hword" id="hw">
                <canvas id="histword" width="600" height="385"></canvas>
            </div>
            <div class="hist hletter" id="hl">
                <canvas id="histletter" width="600" height="385"></canvas>
            </div>
        </div>
    </main>
    <footer class="flexbody acquaintance">
        <div class="flexfooter tie">
            <input type="button" class="but add" id="add" name="add" value="Добавить">
            <input type="button" class="but del" id="del" name="del" value="Удалить">
            <input type="button" class="but index" id="index" name="index" value="Индексировать">
        </div>
    </footer>
</form>
<script src="statisticApp.js" type="text/javascript" charset="utf-8" defer></script>
<script src="http://code.jquery.com/jquery-3.4.1.js" type="text/javascript"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
</body>
</html>
