package com.gmail.yuomelyanchuk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/Registration")
public class RegistrationSrvl extends HttpServlet {
   private UserList userlist = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");
        OutputStream os = resp.getOutputStream();
        if(login ==null || pwd == null){
            os.write("ksjfsjgfsjhgf".getBytes());
            return;
        }
        if (userlist.addUser(login, pwd)) {
            os.write("Registration successful".getBytes());
            userlist.getUser(login).setStatus("offline");

        } else {
            os.write("Login already exists".getBytes());
        }
    }

}
