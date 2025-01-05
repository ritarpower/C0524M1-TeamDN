package com.example.casem4.controller.home;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model) {
        Object user = session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", user);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        return "home";
    }

    private boolean checkIfUserIsLoggedIn() {
        return false;
    }
}
