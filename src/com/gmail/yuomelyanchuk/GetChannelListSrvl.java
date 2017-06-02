package com.gmail.yuomelyanchuk;

import com.gmail.yuomelyanchuk.ChannelList;
import com.gmail.yuomelyanchuk.Message;
import com.gmail.yuomelyanchuk.UserList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

@WebServlet("/channelList")
public class GetChannelListSrvl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String secId = req.getParameter("secId");
        if (secId.equals("" + UserList.getInstance().getUser(login).getUserSession())) {
            Gson gson = new GsonBuilder().create();
            OutputStream os = resp.getOutputStream();
            os.write(gson.toJson(ChannelList.getInstance().getAllChnnelsNames()).getBytes());
        }else {
            OutputStream os = resp.getOutputStream();
            os.write("you are not authorized".getBytes());
        }
    }
}
