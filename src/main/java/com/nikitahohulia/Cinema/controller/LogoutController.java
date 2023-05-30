package com.nikitahohulia.Cinema.controller;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@NoArgsConstructor
public class LogoutController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/logout")
    public String logout(Model theModel)    {
        httpSession.invalidate();
        return "redirect:/home";
    }
}
