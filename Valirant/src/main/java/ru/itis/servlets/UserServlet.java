package ru.itis.servlets;

import ru.itis.models.User;
import ru.itis.repositories.UserRepositories;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class UserServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Arsen2005";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Valorant";

    private List<User> users;

    private UserRepositories usersRepository;

    @Override
    public void init() throws ServletException {

        users = new ArrayList<>();
        User user1 = User.builder()
                .id(1)
                .name("Danil")
                .email("Smirnov")
//                .age(22)
                .build();

        User user2 = User.builder()
                .id(2)
                .name("Danil")
                .email("Sysel")
//                .age(15)
                .build();

        User user3 = User.builder()
                .id(3)
                .name("Kirill")
                .email("Lapshov")
//                .age(30)
                .build();

        users.add(user1);
        users.add(user2);
        users.add(user3);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        System.out.println(firstName);
        resp.sendRedirect(req.getContextPath() + "/profile");

    }
}
