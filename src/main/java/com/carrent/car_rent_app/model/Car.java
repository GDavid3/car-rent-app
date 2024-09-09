package com.carrent.car_rent_app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int daily_price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(int daily_price) {
        this.daily_price = daily_price;
    }

    @OneToOne(mappedBy = "car")
    private Order order;
}
