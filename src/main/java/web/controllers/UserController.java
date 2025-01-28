package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/allUsers";
    }

    @GetMapping("/show")
    public String showUser(Model model, @RequestParam(value = "id", required = false) long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/showUser";
    }


    @GetMapping("/new")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/newUser";
    }

    @PostMapping()
    public String newUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String showEditUserForm(Model model,
                                   @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/editUser";
    }

    @PatchMapping()
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "id", required = false) Long id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }
    @DeleteMapping()
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
