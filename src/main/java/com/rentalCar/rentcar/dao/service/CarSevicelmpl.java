package com.rentalCar.rentcar.dao.service;

import com.rentalCar.rentcar.dao.models.Post;
import com.rentalCar.rentcar.dao.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarSevicelmpl implements CarService {

    private final PostRepository postRepository;

    @Autowired
    public CarSevicelmpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllBody(String string) {
        return postRepository.findAllByBody(string);
    }

    @Override
    public Post findCarById(int id) {
        return postRepository.findCarById(id);
    }

    @Override
    public List<Post> findAllBrand(String string) {
        return postRepository.findAllByBrand(string);

    }@Override
    public List<Post> findAllCarcl(String string) {
        return postRepository.findAllByCarcl(string);
    }

    @Override
    public List<Post> findAllTransmission(String string) {
        return postRepository.findAllByTransmission(string);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public List<Post> findTop4All() {
        return postRepository.findTop4All();
    }

    @Override
    public List<Post> findTop8All() {
        return postRepository.findTop8All();
    }

    @Override
    public List<Post> findTop12All() {
        return postRepository.findTop12All();
    }
}
