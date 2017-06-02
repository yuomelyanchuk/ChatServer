package com.gmail.yuomelyanchuk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userStatus")
public class GetUserStatusSrvl extends HttpServlet {
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
        Gson gson = new GsonBuilder().create();
        os.write(gson.toJson(UserList.getInstance().getUsersStatus()).getBytes());



    }
}
