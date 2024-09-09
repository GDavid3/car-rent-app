package com.carrent.car_rent_app.model;

import jakarta.persistence.*;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int day_count;

    @Column(nullable = false)
    private String start_date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carid", referencedColumnName = "id")
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
