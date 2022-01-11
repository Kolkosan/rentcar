package com.rentalCar.rentcar.controllers;

import com.rentalCar.rentcar.dao.models.Post;
import com.rentalCar.rentcar.dao.repository.ArrangeRepository;
import com.rentalCar.rentcar.dao.repository.ClientRepository;
import com.rentalCar.rentcar.dao.repository.PostRepository;
import com.rentalCar.rentcar.dao.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CarController {
    private PostRepository postRepository;
    private CarService carService;
    private ArrangeRepository arrangeRepository;
    private ClientRepository clientRepository;


    public CarController(PostRepository postRepository, CarService carService, ArrangeRepository arrangeRepository, ClientRepository clientRepository) {
        this.postRepository = postRepository;
        this.carService = carService;
        this.arrangeRepository = arrangeRepository;
        this.clientRepository = clientRepository;
    }
}
