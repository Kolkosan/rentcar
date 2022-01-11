package com.rentalCar.rentcar.controllers;

import com.rentalCar.rentcar.dao.models.Client;
import com.rentalCar.rentcar.dao.models.Role;
import com.rentalCar.rentcar.dao.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
@Controller
public class RegistrationController {
    private final ClientService clientService;

    @Autowired
    public RegistrationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("user",new Client());
        return "registration";
    }


    @PostMapping("/userRegister")
    public String register(@Valid Client user, Errors errors) {
        if (errors.hasErrors()) {
            return "registration";
        }
        user.setRole(Role.USER);
        try {
            clientService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "registration";
        }
        return "login";
    }
}
