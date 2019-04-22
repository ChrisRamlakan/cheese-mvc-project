package com.launchcode.studio.cheesemvc.models;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {
 private static HashMap<Long, User> users = new HashMap<>();

 //
  public static ArrayList<User> getAll(){
    ArrayList<User> userList = new ArrayList<>(users.values());
    return userList;
  }

  public static void addUser(User user){
    users.put(user.getId(), user);
  }

  public static User getUserByID(long id){
    return users.get(id);
  }
}
