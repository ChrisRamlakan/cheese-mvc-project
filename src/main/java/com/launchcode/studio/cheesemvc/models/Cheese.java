package com.launchcode.studio.cheesemvc.models;

public class Cheese {
  private static int nextCheeseID = 0;

  private int cheeseID;
  private String name;
  private String description;

  public Cheese(){
   this.cheeseID= ++nextCheeseID;
  }

  public Cheese(String name, String description){
    this();
    this.name = name;
    this.description = description;
  }

  public int getCheeseID() {
    return cheeseID;
  }

  public void setCheeseID(int cheeseID) {
    this.cheeseID = cheeseID;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
