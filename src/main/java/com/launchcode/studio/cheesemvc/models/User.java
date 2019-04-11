package com.launchcode.studio.cheesemvc.models;

import java.util.Date;

public class User {
  private static int nextID=0;

  private int userID;
  private String username, password, emailAddress;
  private Date joinDate;

  public User(){
  this.userID= ++nextID;
  this.joinDate = new Date();
  }

  public User(String username, String password, String emailAddress) {
    this();
    this.username = username;
    this.password = password;
    this.emailAddress = emailAddress;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }
}

