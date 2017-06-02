package com.gmail.yuomelyanchuk;


import java.util.*;

public class UserList {
    private static final UserList uList = new UserList();
    private  final Map<String,User> userList = Collections.synchronizedMap(new HashMap<>());

    private UserList(){
        userList.put("system",new User("system","My secret password"));
    }
    public static UserList getInstance(){
        return uList;
    }

    public boolean addUser(String login,String pwd){
        if(!userList.containsKey(login)){
            userList.put(login,new User(login,pwd));
            return true;
        }else{
            return false;
        }
    }
    public User getUser(String login){
            return userList.get(login);
    }
    public boolean userExists(String login){
        return userList.containsKey(login);
    }
    public void onChannelDeleting(String chName){
        userList.values().stream().filter(u->u.getChannel().equals(chName)).forEach(u->{
            u.setChannel("public");
            u.setUserSession(-1);
            });
    }
    public List<UserStatus> getUsersStatus(){
        List<UserStatus> userStatus = new ArrayList<>();
        userList.values().stream().forEach(m->userStatus.add(new UserStatus(m.getLogin(),m.getStatus(),m.getChannel())));
        return userStatus;
    }
}
