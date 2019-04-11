package com.launchcode.studio.cheesemvc.models;

import java.util.ArrayList;
import java.util.HashMap;

public class CheeseData {
  //private static ArrayList<Cheese> cheeses = new ArrayList<>();

  //<CheeseID, Cheese>
  private static HashMap<Integer, Cheese> cheeses = new HashMap<>();

  //CRUD Operations:



  //getAll  - R
  public static ArrayList<Cheese> getAll(){
    ArrayList<Cheese> cheeseList = new ArrayList<>(cheeses.values());
    return cheeseList;
  }

  //add - C

  public static void addCheese(Cheese newCheese){
    cheeses.put(newCheese.getCheeseID(),newCheese);
  }

  //remove - D

  public static void removeCheese(int id){
    //Cheese cheeseToRemove = getByID(id);
    cheeses.remove(id);
    //cheeses.remove(cheeseToRemove);
  }

  //getByID - Helper Method

  public static Cheese getByID(int id){
    return cheeses.get(id);
  }



}
