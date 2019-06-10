# IndexSite
<p>Приложение для анализа содержимого сайта.<p>
<p>Тестовое задание ДартИТ.</p>

<h2>Инструменты</h2>
<p>ОС: Windows 7,</p>
<p>Студия: Intellij Idea Ultimate 2018.2.4 x64,</p>
<p>java SDK: jdk1.8.0_191,</p>
<p>Студия БД: dbForge Studio for MySQL,</p>
<p>Сервер БД: MySQL Server 5.5,</p>
<p>Дополнительные библиотеки: см. pom.xml,</p>
<p>Дополнительные инструменты: Corel PHOTO-PAINT X6.</p>

<h2>Инструкция по запуску в Idea</h2>
<p>1) Убедитесь, что у вас включена автозагрузка зависимостей maven 
<a href="http://qaru.site/questions/20701/import-maven-dependencies-i..">Настройка автоимпорта в maven</a></p>
<p>2) Настройте конфигурацию для запуска через maven командой "clean package jetty:run". Проверье, что SDK проекта установлена не ниже 8-версии.</p>
<p>3) Настройте level языка Java в трёх местах <a href="https://stackoverflow.com/questions/12900373/idea-javac-source-release-1-7-requires-target-release-1-7/12900859#12900859">Настройка уровня языка в Идеа</a>. 
Версию JavaScript тоже нужно настраивать в Idea, но для этого проекта достаточно версии по умолчанию.</p>
<p>4) Проверьте подключение к базе данных. В Idea справа в серой рамке слово "Database". Настройте коннект с теми же параметрами, что и в классе ConnectDao. Логин и пароль к базе нужно изменить на ваш.</p>
<p>5) Воспользуйтесь файлом IndexSite.sql для создания базы данных, это можно сделать прямо из Idea (правой кнопкой мыши по названию).
На вкладке Database вы увидите изменения.</p>
<p>6) Запускайте.</p>

<h2>Комментарии</h2>
<p>Я писала проект начиная от базы и заканчивая веб-интерфейсом, поэтому бэкэнд был написан и проверен как простейший проект с jar-файлом в результате компиляции. Затем я добавила Web Application и maven к проекту для формирования веб-приложения. Папку web в таком случае следует переименовать в webapp, это нужно для работы maven.</p>
<p>Фронт на самом деле не использует технологий jsp-jstl-el, html-документы обёрнуты в jsp по некоторым причинам, поэтому вы можете закомментировать первую строку в jsp-файлах и посмотреть фронт отдельно. Можете даже унести его на свой микросервис, достаточно изменить url в js-файлах функций mainpost и goout.</p>
<p>Первая версия содержит некоторые ошибки, я исправлю их в будущих версиях.</p>
<p>Мне не хотелось писать совершенно бессмысленное приложение, поэтому я отклонилась от ТЗ и заложила в архитектуру возможность в будущем безболезненно расширить его функциональность.</p>

<h2>Материалы</h2>
<p><a href="http://www.javaknowledge.info/jsp-servlet-jstl-and-mysql-simple-crud-application/">
Jsp, Servlet, JSTL and MySQL Simple CRUD Application</a></p>
<p><a href="https://tproger.ru/translations/building-a-web-app-with-java-servlets/">Создаём веб-приложение с Java Servlets</a></p>
<p><a href="http://java-online.ru/jsp-jquery.xhtml">Интеграция JSP и jQuery</a></p>
<p><a href="http://qaru.site/questions/2706/how-do-servlets-work-instantiation-sessions-shared-variables-and-multithreading">Как работают сервлеты? Создание, сеансы, общие переменные и многопоточность</a></p>
<p><a href="http://www.javaportal.ru/java/articles/java_http_web/article05.html">Java. HTTP протокол и работа с WEB</a></p>
<p>Этот сайт использовался в качестве справочника по html и css <a href="https://webref.ru/">Руководства по веб-технологиям</a></p>
<p><a href="https://code.tutsplus.com/tutorials/getting-started-with-chartjs-introduction--cms-28278">Getting Started With Chart.js: Introduction</a></p>
<p><a href="https://www.baeldung.com/jackson-object-mapper-tutorial">Intro to the Jackson ObjectMapper</a></p>
<p>Мой основной источник знаний по JavaScript <a href="https://learn.javascript.ru">Современный учебник JavaScript</a>.</p>
<p><a href="https://medium.com/@stasonmars/%D0%BF%D0%BE%D0%BB%D0%BD%D0%BE%D0%B5-%D0%BF%D0%BE%D0%BD%D0%B8%D0%BC%D0%B0%D0%BD%D0%B8%D0%B5-%D1%81%D0%B8%D0%BD%D1%85%D1%80%D0%BE%D0%BD%D0%BD%D0%BE%D0%B3%D0%BE-%D0%B8-%D0%B0%D1%81%D0%B8%D0%BD%D1%85%D1%80%D0%BE%D0%BD%D0%BD%D0%BE%D0%B3%D0%BE-javascript-%D1%81-async-await-ba5f47f4436">Полное понимание синхронного и асинхронного JavaScript с Async/Await</a></p>
<p>Старый, но полезный сайт про java <a href="http://java-course.ru">Java course</a></p>
<p>Видео <a href="https://www.youtube.com/watch?v=LCsIuCGrCL0">Асинхронность в JavaScript. Callback, Promise и Async/Await.</a></p>
<p>Видео <a href="https://www.youtube.com/watch?time_continue=2614&v=SF5yHkfiZkY">Общение с сервером в JavaScript. Ajax и Fetch.</a></p>
<p><a href="https://javarush.ru/groups/posts/1939-comparator-v-java">Comparator в Java</a></p>
