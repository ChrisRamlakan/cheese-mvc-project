package com.launchcode.studio.cheesemvc.controllers;

import com.launchcode.studio.cheesemvc.models.User;
import com.launchcode.studio.cheesemvc.models.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

  //Request Path: GET /user/add
  @RequestMapping(value="add", method = RequestMethod.GET)
  public String add(Model model){
    model.addAttribute("title","Create New User");
    return "user/add";
  }

  @RequestMapping(value="detail/{userID}", method = RequestMethod.GET)
  public String details(Model model, @PathVariable int userID){
    User user = UserManager.getUserByID(userID);
    //Cheese cheeseToEdit = CheeseData.getByID(cheeseID);
    if(user == null) {
      model.addAttribute("title", "User List");
      model.addAttribute("userList", UserManager.getAll());
      return "user/index";
    }
    model.addAttribute("title","User Details");
    model.addAttribute("user", user);
    return "user/detail";
  }

  @RequestMapping(value="add", method = RequestMethod.POST)
  public String add(Model model,
                    @ModelAttribute User user,
                    @RequestParam(name="verifyPassword", required = false) String verify){
    if(user.getPassword().equals(verify)){
      UserManager.addUser(user);
      model.addAttribute("title", "User List");
      model.addAttribute("user", user);
      model.addAttribute("userList", UserManager.getAll());
      return "user/index";
    }
    else{
      model.addAttribute("username", user.getUsername());
      model.addAttribute("emailAddress", user.getEmailAddress());
      model.addAttribute("errorText", "The passwords don't match");
      return "user/add";
    }
  }
}
