package com.rentalCar.rentcar.dao.models;

import javax.persistence.*;


@Entity
public class Post {

    @Id //из java persis
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String  brand, carcl, body,transmission,color,photo,seats,prod_date,cost_day,deposit;
    private String comments;

    public Post(String brand, String carcl, String body, String transmission, String color, String photo, String seats, String prod_date, String cost_day, String deposit, String comments) {
        this.brand = brand;
        this.carcl = carcl;
        this.body = body;
        this.transmission = transmission;
        this.color = color;
        this.photo = photo;
        this.seats = seats;
        this.prod_date = prod_date;
        this.cost_day = cost_day;
        this.deposit = deposit;
        this.comments = comments;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarcl() {
        return carcl;
    }

    public void setCarcl(String carcl) {
        this.carcl = carcl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getProd_date() {
        return prod_date;
    }

    public void setProd_date(String prod_date) {
        this.prod_date = prod_date;
    }

    public String getCost_day() {
        return cost_day;
    }

    public void setCost_day(String cost_day) {
        this.cost_day = cost_day;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
