package com.launchcode.studio.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {

  @Id
  @GeneratedValue
  private long id;

  @NotNull
  @Size(min=3, max=15, message = "Categories must be between 3 and 15 characters long")
  private String name;

  @OneToMany
  @JoinColumn(name = "category_id")
  private List<Cheese> cheeses = new ArrayList<>();

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
}
