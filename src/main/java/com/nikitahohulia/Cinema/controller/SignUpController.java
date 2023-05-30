package com.nikitahohulia.Cinema.controller;

import com.nikitahohulia.Cinema.entities.User;
import com.nikitahohulia.Cinema.entities.UserRole;
import com.nikitahohulia.Cinema.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor

public class SignUpController {

    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/signup")
    public String getLogin(Model theModel){
        theModel.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String postLogin(@ModelAttribute("user") User theUser, Model theModel){
        try {

            User user = userService.singUpUser(theUser);
            List<UserRole> roles = userService.getRoles(user);
            httpSession.setAttribute("currentUser", user);
            httpSession.setAttribute("userRoles", roles);
            return "redirect:/home";
        } catch (ServiceException ex){
            theModel.addAttribute("error", ex.getMessage());
            return "signup";
        }
    }

}
