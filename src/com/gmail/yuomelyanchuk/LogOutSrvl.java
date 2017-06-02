package com.gmail.yuomelyanchuk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/logout")
public class LogOutSrvl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String secId = req.getParameter("secId");
        OutputStream os = resp.getOutputStream();
        if (!secId.equals("" + UserList.getInstance().getUser(login).getUserSession())) {
            os.write("you are not authorized".getBytes());
            return;
        }

        User user= UserList.getInstance().getUser(login);
        user.setStatus("offline");
        user.setUserSession(-1);
    }
}
