package com.gmail.yuomelyanchuk;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class ChannelList {

    private final Gson gson = new GsonBuilder().create();

    private static final ChannelList channels = new ChannelList();

    private final Map<String, Channel> channelList = Collections.synchronizedMap(new HashMap<>());

    private ChannelList() {
        channelList.put("public", new Channel("public", "system"));
        channelList.put("private", new Channel("private", "system"));
    }

    public static ChannelList getInstance() {
        return channels;
    }

    public Channel getChannel(String name) {
        return channelList.get(name);
    }

    public void deleteChannel(String name) {
        channelList.remove(name);
    }

    public void addChannel(String name, String owner) {
        channelList.put(name, new Channel(name, owner));
    }

    public boolean isChannelExists(String name) {
        return channelList.containsKey(name);
    }

    public List<String> getAllChnnelsNames() {
        return channelList.values().stream().map(m -> m.getChannelName()).distinct().sorted().collect(Collectors.toList());

    }

    public void addMessage(Message msg) {

        if (channelList.containsKey(UserList.getInstance().getUser(msg.getFrom()).getChannel()) && msg.getMessageType() != 2) {
            channelList.get(UserList.getInstance().getUser(msg.getFrom()).getChannel()).addMsg(msg);
        } else if (msg.getMessageType() == 2 && UserList.getInstance().userExists(msg.getTo())) {
            channelList.get("private").addMsg(msg);
        }

    }

    public String toJson(String login, int n, int nPrivate) {
        JsonMessages jsonMessages = new JsonMessages();
        User user = UserList.getInstance().getUser(login);

        if (channelList.containsKey(user.getChannel()) && !"private".equals(user.getChannel())
                && channelList.get(user.getChannel()).getMsgList().size() > n) {
            jsonMessages.addMessages(channelList.get(user.getChannel()).getMsgList(), n);
        }

        List<Message> prvMsg =channelList.get("private").getMsgList().stream().filter(m->m.getTo().equals(login))
                .collect(Collectors.toList());


        if (prvMsg.size() > nPrivate) {
            jsonMessages.addMessages(prvMsg, nPrivate);
        }
        if (jsonMessages.getList().size() > 0) {
            return gson.toJson(jsonMessages);
        } else {
            return null;
        }


    }

}
