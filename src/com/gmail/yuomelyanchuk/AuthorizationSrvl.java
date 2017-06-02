package com.gmail.yuomelyanchuk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;


@WebServlet("/Authorization")
public class AuthorizationSrvl extends HttpServlet {
    private UserList userlist = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        User user = userlist.getUser(login);
        try {
            if (user.checkPassword(req.getParameter("pwd"))) {
                user.setUserSession(new Date().getTime());
                Cookie cookie = new Cookie("secId", "" + userlist.getUser(login).getUserSession());
                resp.addCookie(cookie);
                user.setStatus("online");
                OutputStream os = resp.getOutputStream();
                os.write("hi ".concat(login).concat(" :), you are in ").concat(user.getChannel()).concat(" channel").getBytes());
            } else {
                OutputStream os = resp.getOutputStream();
                os.write("password is incorrect".getBytes());
            }
        } catch (NullPointerException ex) {
            OutputStream os = resp.getOutputStream();
            os.write("login not exists".getBytes());
        }

    }
}
