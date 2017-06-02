package com.gmail.yuomelyanchuk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/enterToChannel")
public class EnterToChannelSrvl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String secId = req.getParameter("secId");
        String channelName = req.getParameter("chName");
        if (!secId.equals("" + UserList.getInstance().getUser(login).getUserSession())) {
            OutputStream os = resp.getOutputStream();
            os.write("you are not authorized".getBytes());
            return;
        }

        if(ChannelList.getInstance().isChannelExists(channelName) && !"private".equals(channelName)) {
            UserList.getInstance().getUser(login).setChannel(channelName);
            OutputStream os = resp.getOutputStream();
            os.write("you entered the ".concat(channelName).concat(" channel").getBytes());
        }else{
            OutputStream os = resp.getOutputStream();
            os.write(channelName.concat(" not exist").getBytes());
        }
    }
}
