package com.launchcode.studio.cheesemvc.controllers;

import com.launchcode.studio.cheesemvc.models.Category;
import com.launchcode.studio.cheesemvc.models.data.CategoryDAO;
import com.launchcode.studio.cheesemvc.models.data.CheeseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

  @Autowired
  private CategoryDAO categoryDAO;

  @Autowired
  private CheeseDAO cheeseDAO;

  //Request Path: /category
  @RequestMapping(value="")
  public String indexHandler(Model model){
    model.addAttribute("title","View Categories");
    model.addAttribute("categories", categoryDAO.findAll());
    return "category/index";
  }

  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String displayCategoryAddHandler(Model model) {

    model.addAttribute("title", "Add Category");
    model.addAttribute("category", new Category());
    return "category/add";
  }

  @RequestMapping(value="add", method = RequestMethod.POST)
  public String processCategoryAddHandler(Model model,
                                          @ModelAttribute @Valid Category category,
                                          Errors errors){
    if(errors.hasErrors()){
      model.addAttribute("title", "Add Category");
      return "category/add";
    }
    categoryDAO.save(category);
    return "redirect:";
  }

  @RequestMapping(value="{categoryID}", method = RequestMethod.GET)
  public String displayCategorySearchForm(Model model,
                                          @PathVariable long categoryID){

    model.addAttribute("title", "Search By Category");
    model.addAttribute("cheeses", cheeseDAO.findByCategoryId(categoryID));
    return "cheese/index";
  }
}
