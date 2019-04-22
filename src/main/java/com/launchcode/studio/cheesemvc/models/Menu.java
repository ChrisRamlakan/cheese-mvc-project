package com.launchcode.studio.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="menus")
public class Menu {
  @Id
  @GeneratedValue
  private long id;

  @NotNull
  @Size(min=4, max=25, message="The menu name must be between 4 and 25 characters long")
  private String name;

  @ManyToMany
  private List<Cheese> cheeses = new ArrayList<>();

  public Menu() {
  }

  public Menu(String name) {
    this.name = name;
  }

  public void addItem(Cheese item){
    cheeses.add(item);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Cheese> getCheeses() {
    return cheeses;
  }
}
