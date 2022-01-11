package com.rentalCar.rentcar.dao.repository;

import com.rentalCar.rentcar.dao.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findCarById( int id);
    List<Post> findAllByBrand(String string);
    List<Post> findAllByCarcl(String string);
    List<Post> findAllByBody(String string);
    List<Post> findAllByTransmission(String string);
    @Query(value = "SELECT * from carofrent.post limit 0,4",nativeQuery = true)
    List<Post> findTop4All();
    @Query(value = "SELECT * from carofrent.post limit 4,4",nativeQuery = true)
    List<Post> findTop8All();
    @Query(value = "SELECT * from carofrent.post limit 8,4",nativeQuery = true)
    List<Post> findTop12All();
}
