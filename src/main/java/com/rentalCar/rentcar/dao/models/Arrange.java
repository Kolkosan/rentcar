package com.rentalCar.rentcar.dao.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Arrange {

    @Id //из java persis
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String car,client,lastname,firstname,patronymic;
    private String email, licnumb,passnumb;
    private String licissue,passissue,orderdate,returndate;
    private String country,region,city,address,comments,consideration;

    public Arrange(String car, String client,String lastname, String firstname, String patronymic, String email, String licnumb, String passnumb, String licissue, String passissue, String country, String region, String city, String address,String orderdate,String returndate,String comments,String consideration ) {
        this.car = car;
        this.client = client;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.email = email;
        this.licnumb = licnumb;
        this.passnumb = passnumb;
        this.licissue = passissue;
        this.passissue = passissue;
        this.country = country;
        this.region = region;
        this.city = city;
        this.address = address;
        this.orderdate=orderdate;
        this.returndate=returndate;
        this.comments = comments;
        this.consideration = consideration;
    }

    public Arrange() {
    }

}
