package com.gmail.yuomelyanchuk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/deleteChannel")
public class DeleteChannelSrvl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String secId = req.getParameter("secId");
        String channelName = req.getParameter("chName");
        OutputStream os = resp.getOutputStream();
        if (!secId.equals("" + UserList.getInstance().getUser(login).getUserSession())) {
            os.write("you are not authorized".getBytes());
            return;
        }
        ChannelList ch = ChannelList.getInstance();

        if(!ch.isChannelExists(channelName)){
            os.write("channel not exists".getBytes());
            return;
        }

        if(!ch.getChannel(channelName).getOwner().equals(login)){
            os.write("you are not owner".getBytes());
            return;
        }
        UserList.getInstance().onChannelDeleting(channelName);
        ch.deleteChannel(channelName);
        os.write(channelName.concat(" channel delete successfully").getBytes());



    }
}
