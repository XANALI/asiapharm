package kz.xan.asiapharm.controllers.user;

import kz.xan.asiapharm.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/show-all")
    public String listUsers(Model model){
        model.addAttribute("users", userService.findAll());

        return "users";
    }
}
