package com.example.webapp.controller;

import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Web controller for server-side rendered pages.
 * Uses Thymeleaf template engine for rendering HTML pages.
 */
@Controller
public class WebController {

    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Home page - public access.
     *
     * @return the index template
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Login page.
     *
     * @return the login template
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Home page after authentication.
     * Displays user information and list of users from database.
     *
     * @param principal the authenticated OAuth2 user
     * @param model the model for template rendering
     * @return the home template
     */
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("login", principal.getAttribute("login"));
            model.addAttribute("email", principal.getAttribute("email"));
            model.addAttribute("avatarUrl", principal.getAttribute("avatar_url"));
        }
        
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        
        return "home";
    }

    /**
     * Users page - displays all users from database.
     *
     * @param model the model for template rendering
     * @return the users template
     */
    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
