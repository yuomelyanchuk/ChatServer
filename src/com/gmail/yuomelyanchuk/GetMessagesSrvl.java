package com.gmail.yuomelyanchuk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/messageList")
public class GetMessagesSrvl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String secId = req.getParameter("secId");
        int nChannel = Integer.parseInt(req.getParameter("nChannel"));
        int nPrivate = Integer.parseInt(req.getParameter("nPrivate"));

        OutputStream os = resp.getOutputStream();
        if (!secId.equals("" + UserList.getInstance().getUser(login).getUserSession())) {
            os.write("you are not authorized".getBytes());
            return;
        }
        try {
            os.write(ChannelList.getInstance().toJson(login,nChannel,nPrivate).getBytes());
        }catch (NullPointerException e){
            os.write("empty".getBytes());
        }


    }
}
