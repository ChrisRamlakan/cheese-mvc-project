package com.launchcode.studio.cheesemvc.controllers;

import com.launchcode.studio.cheesemvc.models.Cheese;
import com.launchcode.studio.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
@RequestMapping("cheese")
public class CheeseController {

  //Request Path: /cheese
  @RequestMapping(value="")
  public String index(Model model){
    String title = "My Cheeses";
    model.addAttribute("title", title);
    model.addAttribute("cheeses", CheeseData.getAll());
    return "cheese/index";
  }

  //Request Path: GET /cheese/add
  @RequestMapping(value="add", method = RequestMethod.GET)
  public String add(Model model){
    model.addAttribute("title","Add Cheese");
    return "cheese/add";
  }

  private boolean nameContainsInvalidChars(String cheeseName){
    if(cheeseName.matches("[^a-zA-Z ]"))    {
      return false;
    }
    return true;
  }

  //Request Path: POST /cheese/add
  @RequestMapping(value="add", method = RequestMethod.POST)
  public String addHandler(Model model,
                           @ModelAttribute Cheese newCheese){
    //Check to make sure you aren't adding an empty object to the list (Name is required)
    if(newCheese.getName().isEmpty()){
      model.addAttribute("title","Add Cheese");
      model.addAttribute("errorText","There was an error processing your response");
      return "cheese/add";
    }
     //Validation of cheese name/description
     //TODO Add this back after model binding
//    if(nameContainsInvalidChars(cheeseName)){
//      model.addAttribute("title","Add Cheese");
//      model.addAttribute("errorText","Cheese name contains invalid characters");
//      return "cheese/add";
//    }

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
}
