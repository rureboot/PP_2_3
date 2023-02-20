package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService= userService;
    }


    @RequestMapping("/")
        public String list(Model model){
        List<User> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "userList";
    }

    @RequestMapping("/userInfo")
    public String userInfo(Model model){
        model.addAttribute("user", new User());

        return "userInfo";
    }



    @RequestMapping("/add")
    public String userAdd(@ModelAttribute User user){
        userService.addOrEditUser(user);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) Long id, Model model){
        User userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        return "userInfo";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id){
        userService.deleteUserById(id);
        return "redirect:/";
    }


}
