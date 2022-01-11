package com.rentalCar.rentcar.dao.service;

import com.rentalCar.rentcar.dao.models.Arrange;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface ArrangeService extends UserDetailsService {
    void save(Arrange arrange);
}
