package com.gmail.yuomelyanchuk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Channel {
    private final String channelName;
    private List<Message> msgList = Collections.synchronizedList(new ArrayList<>());
    private final String owner;

    public Channel(String channelName,String owner) {
        this.channelName = channelName;
        this.owner=owner;
    }

    public String getChannelName() {
        return channelName;
    }

    public List<Message> getMsgList() {
        return msgList;
    }

    public void addMsg(Message msg){
        msgList.add(msg);
    }

    public void setMsgList(List<Message> msgList) {
        this.msgList = msgList;
    }

    public String getOwner() {
        return owner;
    }
}
