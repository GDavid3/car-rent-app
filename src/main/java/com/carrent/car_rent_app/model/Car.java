package com.carrent.car_rent_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Order> order;

    public static Car getCarBuild(String image, int daily_price) {
        return Car.builder().image(image).daily_price(daily_price).active(true).build();
    }
}
