package com.rentalCar.rentcar.dao.service;

import com.rentalCar.rentcar.dao.models.Arrange;
import com.rentalCar.rentcar.dao.models.Client;
import com.rentalCar.rentcar.dao.repository.ArrangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ArrangeServicelmpl implements ArrangeService{

    private final ArrangeRepository arrangeRepository;

    @Autowired
    public ArrangeServicelmpl(ArrangeRepository arrangeRepository) {
        this.arrangeRepository = arrangeRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void save(Arrange arrange) {
        arrangeRepository.save(arrange);
    }
}
