package com.carrent.car_rent_app.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@Table(name = "cars")
public class Car {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int daily_price;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private Order order;
}
