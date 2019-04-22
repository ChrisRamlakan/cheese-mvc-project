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
import java.util.ArrayList;


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

    //TODO Check
    if(errors.hasErrors()){
      model.addAttribute("title","Add Cheese");
      model.addAttribute("categories", categoryDAO.findAll());
      return "cheese/add";
    }
    Category category = categoryDAO.findOne(categoryId);
    newCheese.setCategory(category);
    cheeseDAO.save(newCheese);
//    CheeseData.addCheese(newCheese);
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
//     for(int cheeseID : cheeseIDs){
//       cheeseDAO.delete(cheeseID);
////       CheeseData.removeCheese(cheeseID);
//     }
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
                                @RequestParam(name="cheeseID") int cheeseID,
                                @ModelAttribute @Valid Cheese cheeseToEdit,
                                Errors errors){

    if(errors.hasErrors()){
      // If the field with errors is name or description, set the initial value of
      //  the re-rendered form to the last known good value
      if (errors.hasFieldErrors("name")){
        cheeseToEdit.setName(cheeseDAO.findOne(cheeseID).getName());
//        cheeseToEdit.setName(CheeseData.getByID(cheeseID).getName());
      }
      if (errors.hasFieldErrors("description")){
        cheeseToEdit.setName(cheeseDAO.findOne(cheeseID).getDescription());

//        cheeseToEdit.setDescription(CheeseData.getByID(cheeseID).getDescription());
      }
      model.addAttribute("cheese", cheeseToEdit);
      model.addAttribute("title"," Edit Cheeses");
      model.addAttribute("categories", categoryDAO.findAll());
      return "cheese/edit";
    }
    Cheese cheeseToUpdate = cheeseDAO.findOne(cheeseID);
    cheeseToUpdate.setName(cheeseToEdit.getName());
    cheeseToUpdate.setDescription(cheeseToEdit.getDescription());
    //cheeseToUpdate.setType(cheeseToEdit.getType());
    //CheeseData.updateCheese(cheeseID, , , );
    return "redirect:";
  }

//  @RequestMapping(value="search/category", method = RequestMethod.GET)
//  public String displayCategorySearchForm(Model model){
//    model.addAttribute("title", "Search By Category");
//    model.addAttribute("cheeseTypes", CheeseType.values());
//    return "/cheese/search";
//  }
//
//  @RequestMapping(value="search/category", method = RequestMethod.POST)
//  public String categorySearchFormHandler(Model model,
//                                          @ModelAttribute CheeseType type){
//    model.addAttribute("cheeses", CheeseData.getCheeseByCategory(type));
//    model.addAttribute("title", "Search By Category");
//    model.addAttribute("cheeseTypes", CheeseType.values());
//    return "/cheese/search";
//
//  }
}
