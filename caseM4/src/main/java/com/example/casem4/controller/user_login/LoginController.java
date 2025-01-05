package com.example.casem4.controller.user_login;

import com.example.casem4.model.AppUserDTO;
import com.example.casem4.service.AppUser.AppUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("appUserDTO") AppUserDTO appUserDTO, HttpSession session, Model model) {
        try {
            if ("admin".equals(appUserDTO.getUsername()) && "admin".equals(appUserDTO.getPassword())) {
                session.setAttribute("loggedInUser", "admin");
                return "redirect:/admin-home";
            }
            boolean isValidUser = appUserService.authenticateUser(appUserDTO.getUsername(), appUserDTO.getPassword());
            if (isValidUser) {
                session.setAttribute("loggedInUser", appUserDTO.getUsername());
                return "redirect:/";
            } else {
                model.addAttribute("error", "Tài khoản hoặc mật khẩu không đúng.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi khi đăng nhập.");
            return "login";
        }
    }

}
