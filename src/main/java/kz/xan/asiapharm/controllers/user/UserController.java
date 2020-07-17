package kz.xan.asiapharm.controllers.user;

import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.converters.UserCommandToUser;
import kz.xan.asiapharm.converters.UserToUserCommand;
import kz.xan.asiapharm.domain.User;
import kz.xan.asiapharm.services.ImageService;
import kz.xan.asiapharm.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserToUserCommand toUserCommand;
    private final UserCommandToUser fromUserCommand;
    private final ImageService imageService;

    public UserController(UserService userService, UserToUserCommand toUserCommand, UserCommandToUser fromUserCommand, ImageService imageService) {
        this.userService = userService;
        this.toUserCommand = toUserCommand;
        this.fromUserCommand = fromUserCommand;
        this.imageService = imageService;
    }

    @RequestMapping("/show-all")
    public String listUsers(Model model){
        model.addAttribute("users", userService.findAll());

        return "user/users";
    }

    @RequestMapping("/{id}/showInfo")
    public String getUser(@PathVariable Long id, Model model){
        model.addAttribute("userCommand", toUserCommand.convert(userService.findById(id)));

        return "user/singleInfo";
    }

    @RequestMapping("/{id}/editInfo")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("userCommand", toUserCommand.convert(userService.findById(id)));

        return "user/editInfo";
    }

    @RequestMapping("/newInfo")
    public String newUser(Model model){
        model.addAttribute("userCommand", new UserCommand());

        return "user/newInfo";
    }

    @PostMapping("/{id}/editInfo")
    public String postEditedUser(@PathVariable Long id, @ModelAttribute UserCommand userCommand){
        User oldUser = userService.findById(id);
        User convertedUser = fromUserCommand.convert(userCommand);
        if(oldUser != null){
            convertedUser.setImage(oldUser.getImage());
        }
        User user = userService.save(convertedUser);

        return "redirect:/user/" + id + "/showInfo";
    }

    @PostMapping("/newInfo")
    public String postNewUser(@ModelAttribute UserCommand userCommand){
        User user = userService.save(fromUserCommand.convert(userCommand));

        return "redirect:/user/" + user.getId() + "/showInfo";
    }

    @RequestMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id){
        userService.deleteById(id);

        return "redirect:/user/show-all";
    }
}
