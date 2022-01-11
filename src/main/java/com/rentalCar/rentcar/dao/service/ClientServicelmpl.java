package com.rentalCar.rentcar.dao.service;

import com.rentalCar.rentcar.dao.models.Client;
import com.rentalCar.rentcar.dao.models.Role;
import com.rentalCar.rentcar.dao.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Service
public class ClientServicelmpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ClientServicelmpl(ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(Client client) {
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
    }
    @Override
    public Client findUserByUsername(String userName) {
        return clientRepository.findClientByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client user = clientRepository.findClientByUserName(username);
        Set<Role> roles = new HashSet<>();
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        } else
            System.out.println("SUCCESS");
        roles.add(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), roles);
    }
}
