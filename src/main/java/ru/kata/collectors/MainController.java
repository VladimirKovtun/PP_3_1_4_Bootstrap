package ru.kata.collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.dto.UserDto;
import ru.kata.services.RoleService;
import ru.kata.services.UserService;


import java.security.Principal;

@Controller
@RequestMapping("/main")
public class MainController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public MainController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping()
    public String adminPage(Principal principal, @ModelAttribute("userForReg") UserDto userForReg, Model model) {
        model.addAttribute("usersList", userService.showAllUsers());
        model.addAttribute("allRoles", roleService.findAllRoles());
        model.addAttribute("currentUser", userService.findByEmail(principal.getName()).get());
        return "main_page";
    }

    @PostMapping()
    public String registerUser(@ModelAttribute("userForReg") UserDto userForReg) {
        userService.addUser(UserDto.toUser(userForReg));
        return "redirect:/main";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") UserDto user) {
        userService.editUser(UserDto.toUser(user));
        return "redirect:/main";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/main";
    }
}
