package com.launchcode.studio.cheesemvc.controllers;

import com.launchcode.studio.cheesemvc.models.Category;
import com.launchcode.studio.cheesemvc.models.Cheese;
import com.launchcode.studio.cheesemvc.models.data.CategoryDAO;
import com.launchcode.studio.cheesemvc.models.data.CheeseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("cheese")
public class CheeseController {
  @Autowired
  private CheeseDAO cheeseDAO;

  @Autowired
  CategoryDAO categoryDAO;

  //Request Path: /cheese
  @RequestMapping(value="")
  public String index(Model model){
    model.addAttribute("title"," My Cheeses");
    model.addAttribute("cheeses", cheeseDAO.findAll());
    return "cheese/index";
  }

  //Request Path: GET /cheese/add
  @RequestMapping(value="add", method = RequestMethod.GET)
  public String add(Model model){
    model.addAttribute("title","Add Cheese");
    model.addAttribute(new Cheese());
    model.addAttribute("categories", categoryDAO.findAll());
    return "cheese/add";
  }

  //Request Path: POST /cheese/add
  @RequestMapping(value="add", method = RequestMethod.POST)
  public String addHandler(Model model,
                           @ModelAttribute @Valid Cheese newCheese,
                           @RequestParam long categoryId,
                           Errors errors){

    if(errors.hasErrors()){
      model.addAttribute("title","Add Cheese");
      model.addAttribute("categories", categoryDAO.findAll());
      return "cheese/add";
    }
    Category category = categoryDAO.findOne(categoryId);

    newCheese.setCategory(category);
    cheeseDAO.save(newCheese);
    return "redirect:";
  }

  @RequestMapping(value="remove", method = RequestMethod.GET)
  public String remove(Model model){
    model.addAttribute("title", "Remove Cheese");
    model.addAttribute("cheeses", cheeseDAO.findAll());// CheeseData.getAll());
    return "cheese/remove";
  }

  @RequestMapping(value="remove", method = RequestMethod.POST)
  public String removeFormHandler(Model model,
                                  @RequestParam(name="cheeseIDs", required = false) int[] cheeseIDs){
    if(cheeseIDs == null){
      return "redirect:remove";
    }
    else{
      cheeseDAO.deleteMany(cheeseIDs);
    }
    return "redirect:";
  }
  
  @RequestMapping(value="edit/{cheeseID}", method = RequestMethod.GET)
  public String displayEditForm(Model model, @PathVariable int cheeseID){
    Cheese cheeseToEdit = cheeseDAO.findOne(cheeseID);// CheeseData.getByID(cheeseID);
    if(cheeseToEdit == null) {
      return "redirect:";
    }
    model.addAttribute("title"," Edit Cheeses");
    model.addAttribute("cheese", cheeseToEdit);
    model.addAttribute("categories", categoryDAO.findAll());
    return "cheese/edit";
  }
  
  @RequestMapping(value="edit", method = RequestMethod.POST)
  public String processEditForm(Model model,
                                @ModelAttribute @Valid Cheese cheeseToEdit,
                                Errors errors){

    if(errors.hasErrors()){
      model.addAttribute("cheese", cheeseToEdit);
      model.addAttribute("title"," Edit Cheeses");
      model.addAttribute("categories", categoryDAO.findAll());
      return "cheese/edit";
    }
    System.out.println(cheeseToEdit.getId());
    Cheese cheeseToUpdate = cheeseDAO.findOne(cheeseToEdit.getId());
    if (cheeseToUpdate==null){
      model.addAttribute("cheese", cheeseToEdit);
      model.addAttribute("title"," Edit Cheeses");
      model.addAttribute("categories", categoryDAO.findAll());
      return "cheese/edit";
    }
    cheeseToUpdate.setName(cheeseToEdit.getName());
    cheeseToUpdate.setDescription(cheeseToEdit.getDescription());
    cheeseDAO.save(cheeseToUpdate);
    System.out.println(cheeseToUpdate.getDescription());
    return "redirect:";
  }
}
