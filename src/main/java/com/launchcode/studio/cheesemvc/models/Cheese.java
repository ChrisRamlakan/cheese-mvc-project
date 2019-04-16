package com.launchcode.studio.cheesemvc.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {
  private static int nextCheeseID = 0;

  private int cheeseID;

  @NotNull
  @Size(min=3, max=15)
  private String name;

  @NotNull
  @Size(min=1, message="Description must not be empty")
  private String description;

//  @NotNull
//  @Size(min=1, max=5, message = "Rating must be between 1 and 5")
//  private int rating;
  
  private CheeseType type;
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

  public CheeseType getType() {
    return type;
  }

  public void setType(CheeseType type) {
    this.type = type;
  }
}
