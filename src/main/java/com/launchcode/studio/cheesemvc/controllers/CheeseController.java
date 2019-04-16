package com.launchcode.studio.cheesemvc.controllers;

import com.launchcode.studio.cheesemvc.models.Cheese;
import com.launchcode.studio.cheesemvc.models.CheeseData;
import com.launchcode.studio.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("cheese")
public class CheeseController {

  //Request Path: /cheese
  @RequestMapping(value="")
  public String index(Model model){
    model.addAttribute("title"," My Cheeses");
    model.addAttribute("cheeses", CheeseData.getAll());
    return "cheese/index";
  }

  //Request Path: GET /cheese/add
  @RequestMapping(value="add", method = RequestMethod.GET)
  public String add(Model model){
    model.addAttribute("title","Add Cheese");
    model.addAttribute(new Cheese());
    model.addAttribute("cheeseTypes", CheeseType.values());
    return "cheese/add";
  }

  //Request Path: POST /cheese/add
  @RequestMapping(value="add", method = RequestMethod.POST)
  public String addHandler(Model model,
                           @ModelAttribute @Valid Cheese newCheese,
                           Errors errors){

    if(errors.hasErrors()){
      model.addAttribute("title","Add Cheese");
      model.addAttribute("cheeseTypes", CheeseType.values());
      return "cheese/add";
    }
    CheeseData.addCheese(newCheese);
    return "redirect:";
  }

  @RequestMapping(value="remove", method = RequestMethod.GET)
  public String remove(Model model){
    model.addAttribute("title", "Remove Cheese");
    model.addAttribute("cheeses", CheeseData.getAll());
    return "cheese/remove";
  }

  @RequestMapping(value="remove", method = RequestMethod.POST)
  public String removeFormHandler(Model model,
                                  @RequestParam(name="cheeseIDs", required = false) int[] cheeseIDs){
    if(cheeseIDs == null){
      return "redirect:remove";
    }
    else{
     for(int cheeseID : cheeseIDs){
       CheeseData.removeCheese(cheeseID);
     }
    }
    return "redirect:";
  }
  
  @RequestMapping(value="edit/{cheeseID}", method = RequestMethod.GET)
  public String displayEditForm(Model model, @PathVariable int cheeseID){
    Cheese cheeseToEdit = CheeseData.getByID(cheeseID);
    if(cheeseToEdit == null) {
      return "redirect:";
    }
    model.addAttribute("title"," Edit Cheeses");
    model.addAttribute("cheese", cheeseToEdit);
    model.addAttribute("cheeseTypes", CheeseType.values());
    return "cheese/edit";
  }
  
  @RequestMapping(value="edit", method = RequestMethod.POST)
  public String processEditForm(Model model,
                                @RequestParam(name="cheeseID") int cheeseID,
                                @ModelAttribute @Valid Cheese cheeseToEdit,
                                Errors errors){

    if(errors.hasErrors()){
      model.addAttribute("cheese", CheeseData.getByID(cheeseID));
      model.addAttribute("title"," Edit Cheeses");
      model.addAttribute("cheeseTypes", CheeseType.values());
      return "cheese/edit";
      //return "redirect:/cheese/edit/" + cheeseID;
    }
    CheeseData.updateCheese(cheeseID, cheeseToEdit.getName(), cheeseToEdit.getDescription(), cheeseToEdit.getType());
    return "redirect:";
  }
}
