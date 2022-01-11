package com.rentalCar.rentcar.dao.service;

import com.rentalCar.rentcar.dao.models.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService {
    Client findUserByUsername(String userName);
    void save(Client client);
}
