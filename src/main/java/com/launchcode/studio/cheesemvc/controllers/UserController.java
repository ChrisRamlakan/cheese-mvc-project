package com.launchcode.studio.cheesemvc.controllers;

import com.launchcode.studio.cheesemvc.models.User;
import com.launchcode.studio.cheesemvc.models.UserManager;
import com.launchcode.studio.cheesemvc.models.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
  @Autowired
  private UserDAO userDAO;

  //Request Path: GET /user/add
  @RequestMapping(value="add", method = RequestMethod.GET)
  public String add(Model model){
    model.addAttribute("title","Create New User");
    model.addAttribute(new User());
    return "user/add";
  }

  @RequestMapping(value="detail/{userID}", method = RequestMethod.GET)
  public String details(Model model, @PathVariable long userID){
    User user = userDAO.findOne(userID);
    //User user = UserManager.getUserByID(userID);
    //Cheese cheeseToEdit = CheeseData.getByID(cheeseID);
    if(user == null) {
      model.addAttribute("title", "User List");
      model.addAttribute("userList", userDAO.findAll());
      return "user/index";
    }
    model.addAttribute("title","User Details");
    model.addAttribute("user", user);
    return "user/detail";
  }

  @RequestMapping(value="add", method = RequestMethod.POST)
  public String add(Model model,
                    @ModelAttribute @Valid User user,
                    Errors errors,
                    @RequestParam(name="verifyPassword", required = false) String verify){
    if(errors.hasErrors()){
      if(errors.hasErrors()){
        model.addAttribute("title","Add User");
        model.addAttribute(user);
        return "user/add";
      }
    }
    userDAO.save(user);
    //UserManager.addUser(user);
    model.addAttribute("title", "User List");
    model.addAttribute("user", user);
    model.addAttribute("userList", userDAO.findAll());
    return "user/index";
  }
}
