package com.launchcode.studio.cheesemvc.controllers;

import com.launchcode.studio.cheesemvc.models.Cheese;
import com.launchcode.studio.cheesemvc.models.Forms.AddMenuItemForm;
import com.launchcode.studio.cheesemvc.models.Menu;
import com.launchcode.studio.cheesemvc.models.data.CheeseDAO;
import com.launchcode.studio.cheesemvc.models.data.MenuDAO;
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
@RequestMapping("menu")
public class MenuController {
  @Autowired
  CheeseDAO cheeseDAO;

  @Autowired
  MenuDAO menuDAO;

  //Request Path: /menu
  @RequestMapping(value="")
  public String index(Model model){
    model.addAttribute("title"," Menus");
    model.addAttribute("menus", menuDAO.findAll());
    return "menu/index";
  }

  @RequestMapping(value="add")
  public String addMenu(Model model){
    model.addAttribute("title","Add New Menu");
    model.addAttribute("menu", new Menu());
    return "menu/add";
  }

  @RequestMapping(value="add", method=RequestMethod.POST)
  public String addMenuHandler(Model model,
                               @ModelAttribute @Valid Menu menu,
                               Errors errors){
    if(errors.hasErrors()){
      model.addAttribute("title","Add New Menu");
      return "menu/add";
    }
    menuDAO.save(menu);
    return "redirect:";
  }

  @RequestMapping(value="view/{menuID}")
  public String viewMenu(Model model,
                         @PathVariable long menuID){
    Menu menuToView = menuDAO.findOne(menuID);
    if(menuToView==null){
      return "redirect:";
    }
    model.addAttribute("title",menuToView.getName());
    model.addAttribute("menu", menuToView);
    return "menu/view";
  }

  @RequestMapping(value="add-item/{menuID}")
  public String addItem(Model model,
                         @PathVariable long menuID){
    Menu menuToAddItem = menuDAO.findOne(menuID);
    if(menuToAddItem==null){
      model.addAttribute("title"," Menus");
      model.addAttribute("menus", menuDAO.findAll());
    }
    AddMenuItemForm addMenuItemForm = new AddMenuItemForm(menuToAddItem, cheeseDAO.findAll());
    model.addAttribute("title", "Add item to menu: " + menuToAddItem.getName());
    model.addAttribute("form", addMenuItemForm);
    return "menu/add-item";
  }

  @RequestMapping(value="add-item", method = RequestMethod.POST)
  public String addItemHandler(Model model,
                               @ModelAttribute @Valid AddMenuItemForm addMenuItemForm,
                               Errors errors){
    if(errors.hasErrors()){
      model.addAttribute("title", "Add item to menu: " + addMenuItemForm.getMenu().getName());
      return "menu/add-item";
    }
//    System.out.println("Adding to menuID: " +addMenuItemForm.getMenuID());
//    System.out.println("Adding to menuID2: " +addMenuItemForm.getMenu().getId());
//    System.out.println("Adding cheese: " +addMenuItemForm.getCheeseID());
    Menu menuToAddTo = menuDAO.findOne(addMenuItemForm.getMenuID());
    Cheese cheeseToAdd = cheeseDAO.findOne(addMenuItemForm.getCheeseID());

    if (menuToAddTo == null || cheeseToAdd == null){
      model.addAttribute("title", "Add item to menu: " + addMenuItemForm.getMenu().getName());
      return "menu/add-item";
    }
    menuToAddTo.addItem(cheeseToAdd);
    menuDAO.save(menuToAddTo);
    model.addAttribute("title",menuToAddTo.getName());
    model.addAttribute("menu", menuToAddTo);
    return "menu/view";
  }
}
