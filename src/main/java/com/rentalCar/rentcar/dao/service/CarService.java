package com.rentalCar.rentcar.dao.service;

import com.rentalCar.rentcar.dao.models.Post;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CarService extends UserDetailsService {
    Post findCarById(int id);
    List<Post> findAllBody(String string);
    List<Post> findAllCarcl(String string);
    List<Post> findAllBrand(String string);
    List<Post> findAllTransmission(String string);
    List<Post> findTop4All();
    List<Post> findTop8All();
    List<Post> findTop12All();
}
