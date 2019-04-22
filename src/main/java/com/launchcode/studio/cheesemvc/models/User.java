package com.launchcode.studio.cheesemvc.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="users")
public class User {
  //private static int nextID=0;

  @Id
  @GeneratedValue
  private long id;

  @NotNull
  @Size(min=5, max =15, message = "Username must be between 5 and 15 characters long")
  private String username;
  @Size(min=1, message = "Password field cannot be blank")
  private String password;

  @NotNull(message = "The passwords do not match")
  @Transient
  private String verifyPassword;
  
  @Email
  private String emailAddress;
  private Date joinDate;

  public User(){
  this.joinDate = new Date();
  }

  public User(String username, String password, String emailAddress) {
    this();
    this.username = username;
    this.password = password;
    this.emailAddress = emailAddress;
  }

  //If the passwords don't match, set verifyPassword to null
  private void checkPassword(){
    if (this.password != null && this.verifyPassword != null){
      if(!this.password.equals(this.verifyPassword)){
        this.verifyPassword=null;
      }
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
    //this is not required if the form is processed in order.
    checkPassword();
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

  public String getVerifyPassword() {
    return verifyPassword;
  }

  public void setVerifyPassword(String verifyPassword) {
    this.verifyPassword = verifyPassword;
    checkPassword();
  }
}

