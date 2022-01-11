package com.rentalCar.rentcar.dao.repository;

import com.rentalCar.rentcar.dao.models.Arrange;
import com.rentalCar.rentcar.dao.models.Client;
import com.rentalCar.rentcar.dao.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArrangeRepository extends JpaRepository<Arrange, Integer> {


    @Query(value = "SELECT * from carofrent.arrange c where  c.client = :client_id", nativeQuery = true)
    List<Arrange> findArrangesById(@Param("client_id") String clientid);
}
