package com.launchcode.studio.cheesemvc.models.Forms;

import com.launchcode.studio.cheesemvc.models.Cheese;
import com.launchcode.studio.cheesemvc.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
  private Menu menu;
  private Iterable<Cheese> cheeses;

  @NotNull
  private long menuID;

  @NotNull
  private int cheeseID;

  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public Iterable<Cheese> getCheeses() {
    return cheeses;
  }

  public void setCheeses(Iterable<Cheese> cheeses) {
    this.cheeses = cheeses;
  }

  public long getMenuID() {
    return menuID;
  }

  public void setMenuID(long menuID) {
    this.menuID = menuID;
  }

  public int getCheeseID() {
    return cheeseID;
  }

  public void setCheeseID(int cheeseID) {
    this.cheeseID = cheeseID;
  }

  public AddMenuItemForm() {
  }

  public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
    this.menu = menu;
    this.cheeses = cheeses;
  }
}
