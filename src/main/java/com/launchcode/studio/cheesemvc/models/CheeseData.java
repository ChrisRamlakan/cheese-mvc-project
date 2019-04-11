package com.launchcode.studio.cheesemvc.models;

import java.util.ArrayList;

public class CheeseData {
  private static ArrayList<Cheese> cheeses = new ArrayList<>();

  //getAll
  public static ArrayList<Cheese> getAll(){
    return cheeses;
  }

  //add

  public static void addCheese(Cheese newCheese){
    cheeses.add(newCheese);
  }

  //remove

  public static void removeCheese(int id){
    Cheese cheeseToRemove = getByID(id);
    cheeses.remove(cheeseToRemove);
  }

  //getByID

  public static Cheese getByID(int id){
    Cheese theCheese = null;
    for(Cheese cheese : cheeses) {
      if (cheese.getCheeseID() == id) {
        theCheese = cheese;
      }
    }
    return theCheese;
  }



}
