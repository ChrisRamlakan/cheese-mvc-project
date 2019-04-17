package com.launchcode.studio.cheesemvc.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CheeseData {
  //private static ArrayList<Cheese> cheeses = new ArrayList<>();

  //<CheeseID, Cheese>
  private static HashMap<Integer, Cheese> cheeses = new HashMap<>();

  //CRUD Operations:

  //add - C
  public static void addCheese(Cheese newCheese){
    cheeses.put(newCheese.getCheeseID(),newCheese);
  }


  //getAll  - R
  public static ArrayList<Cheese> getAll(){
    ArrayList<Cheese> cheeseList = new ArrayList<>(cheeses.values());
    return cheeseList;
  }

  //updateCheese - U
  public static void updateCheese(int id, String name, String description, CheeseType type){
    Cheese cheeseToUpdate = CheeseData.getByID(id);
    cheeseToUpdate.setName(name);
    cheeseToUpdate.setDescription(description);
    cheeseToUpdate.setType(type);
    removeCheese(id);
    addCheese(cheeseToUpdate);
  }


  //remove - D
  public static void removeCheese(int id){
    cheeses.remove(id);
  }

  //getByID - Helper Method
  public static Cheese getByID(int id){
    return cheeses.get(id);
  }

  public static Collection<Cheese> getCheeseByCategory(CheeseType cheeseCategory){
    Collection<Cheese> searchResults = new ArrayList<>();
    for (Cheese cheese : cheeses.values()){
      cheese.getType();
      if(cheese.getType().equals(cheeseCategory)){
        searchResults.add(cheese);
      }
    }
    return searchResults;
  }
}
