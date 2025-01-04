package com.example.casem4.controller.user_login;

import com.example.casem4.model.AppUserDTO;
import com.example.casem4.service.AppUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("appUserDTO", new AppUserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("appUserDTO") AppUserDTO appUserDTO, Model model) {
        if (appUserService.checkUserByEmail(appUserDTO.getEmail())) {
            model.addAttribute("error", "Email đã tồn tại trong hệ thống.");
            return "register";
        }
        boolean isRegistered = appUserService.registerUser(appUserDTO);
        if (isRegistered) {
            return "redirect:/login?success";
        } else {
            model.addAttribute("error", "Đã có lỗi xảy ra khi đăng ký.");
            return "register";
        }
    }
}
