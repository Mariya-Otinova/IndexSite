package Controller;

import Entity.User;
import Service.UserService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/ingress.jsp").forward(request, response);
        System.out.println("Открылась главная страница");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Пост запрос главной страницы");
        User user = new User();
        UserService userService = new UserService();
        HttpSession session = request.getSession();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String jsonString;
        MyAnswer myAnswer = new MyAnswer();
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = request.getReader();
        String jsonReq = br.readLine();
        System.out.println("реквест прислал это "+jsonReq);
        MyQuestion myQuestion = mapper.readValue(jsonReq, MyQuestion.class);
        System.out.println(myQuestion.toString());
        String ACTION = myQuestion.getAction();

        if (ACTION.equals("registration")) {
            user.setLogin(myQuestion.getLogin());
            user.setPassword(myQuestion.getPassword());
            String MESSAGE = userService.registration(user);
            myAnswer.setMessage(MESSAGE);
            jsonString = mapper.writeValueAsString(myAnswer);
            out.write(jsonString);
            out.flush();
            out.close();
            System.out.println("Регистрация");
        }

        if (ACTION.equals("authorization")) {
            user.setLogin(myQuestion.getLogin());
            user.setPassword(myQuestion.getPassword());
            String MESSAGE = userService.authorization(user);
            myAnswer.setMessage(MESSAGE);
            jsonString = mapper.writeValueAsString(myAnswer);
            out.write(jsonString);
            out.flush();
            out.close();
            System.out.println("Авторизация");
        }

        if (ACTION.equals("deauthorization")) {
            userService.deauthorization(user);
            System.out.println("Выход из приложения");
        }

        if (ACTION.equals("changepassword")) {
            user.setLogin(myQuestion.getLogin());
            user.setPassword(myQuestion.getPassword());
            String newPassword = myQuestion.getNewpassword();
            String MESSAGE = userService.changePassword(user,newPassword);
            myAnswer.setMessage(MESSAGE);
            jsonString = mapper.writeValueAsString(myAnswer);
            out.write(jsonString);
            out.flush();
            out.close();
            System.out.println("Изменение пароля");
        }

        session.setAttribute("user",user);
    }

}
