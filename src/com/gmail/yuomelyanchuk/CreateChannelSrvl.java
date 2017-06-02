package com.gmail.yuomelyanchuk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/createChannel")
public class CreateChannelSrvl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String secId = req.getParameter("secId");
        String channelName = req.getParameter("chName");

        System.out.println(req.getCharacterEncoding());
        System.out.println(resp.getCharacterEncoding());

        if (!secId.equals("" + UserList.getInstance().getUser(login).getUserSession())) {
            OutputStream os = resp.getOutputStream();
            os.write("you are not authorized".getBytes());
            return;
        }

        if(ChannelList.getInstance().isChannelExists(channelName)){
            OutputStream os = resp.getOutputStream();
            os.write("channel already exists".getBytes());
        }else{
            ChannelList.getInstance().addChannel(channelName,login);
            OutputStream os = resp.getOutputStream();
            os.write( channelName.concat(" channel was created successfully").getBytes());
        }

    }
}
