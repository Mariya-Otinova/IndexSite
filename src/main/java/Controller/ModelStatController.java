package Controller;

import Entity.ModelStat;
import Entity.Site;
import Entity.User;
import Service.IndexbotService;
import Service.SiteService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ModelStatController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Get запрос к станице статистики");
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
        User user = (User)session.getAttribute("user");
            System.out.println("Проверка существования пользователя "+user.toString());
        if (user.getAuthorizationstatus().equals("authorized")) {
            request.getRequestDispatcher("/statistic.jsp").forward(request, response);
            System.out.println("Открылась страница статистики для пользователя "+user.toString());
        }
        } else {
            request.getRequestDispatcher("/ingress.jsp").forward(request, response);
            System.out.println("Возврат на страницу входа при попытке просмотра статистики неавторизованным пользователем");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Site site = new Site();
            SiteService siteService = new SiteService();
            IndexbotService indexbotService = new IndexbotService();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String jsonString;
            MyAnswer myAnswer = new MyAnswer();
            ObjectMapper mapper = new ObjectMapper();
            MyQuestion myQuestion = mapper.readValue(request.getReader(), MyQuestion.class);
            String ACTION = myQuestion.getAction();

            if (ACTION.equals("dataview")) {
                ArrayList<ModelStat> STAT = indexbotService.viewStatistics(user);
                jsonString = mapper.writeValueAsString(STAT);
                out.write(jsonString);
                out.flush();
                out.close();
                System.out.println(jsonString);
            }

            if (ACTION.equals("addstatistics")) {
                site.setUrl(myQuestion.getUrl());
                String REPORT = indexbotService.addStatistics(user, site);
                myAnswer.setMessage(REPORT);
                jsonString = mapper.writeValueAsString(myAnswer);
                out.write(jsonString);
                out.flush();
                out.close();
                System.out.println("Подсчёт статистики пользователем " + user.toString());
            }

            if (ACTION.equals("addsite")) {
                site.setUrl(myQuestion.getUrl());
                String NOTICE = siteService.addSite(user, site);
                myAnswer.setMessage(NOTICE);
                jsonString = mapper.writeValueAsString(myAnswer);
                out.write(jsonString);
                out.flush();
                out.close();
                System.out.println("Добавление сайта пользователем " + user.toString());
            }

            if (ACTION.equals("deletesite")) {
                site.setUrl(myQuestion.getUrl());
                String NOTICE = siteService.deleteSite(user, site);
                myAnswer.setMessage(NOTICE);
                jsonString = mapper.writeValueAsString(myAnswer);
                out.write(jsonString);
                out.flush();
                out.close();
                System.out.println("Удаление сайта пользователем " + user.toString());
            }
        }
    }

}
