package com.gmail.yuomelyanchuk;


public class User {
    private final String login;
    private final String password;
    private String status;
    private String channel="public";
    private  long userSession;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.channel="public";
    }

    public String getLogin() {
        return login;
    }

    public boolean checkPassword(String password){
        return  this.password.equals(password);
    }

    public String getStatus() {
        return status;
    }

    public long getUserSession() {
        return userSession;
    }

    public void setUserSession(long userSession) {
        this.userSession = userSession;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
