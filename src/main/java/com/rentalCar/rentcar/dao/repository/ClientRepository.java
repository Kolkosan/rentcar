package com.rentalCar.rentcar.dao.repository;

import com.rentalCar.rentcar.dao.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findClientByUserName(String userName);



    @Query(value = "SELECT c.id from carofrent.client c where  c.user_name = :userName", nativeQuery = true)
    String findIdByName(@Param("userName") String username);
}
