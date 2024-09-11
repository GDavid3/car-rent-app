package com.carrent.car_rent_app.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Builder
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private int day_count;

    @Column(nullable = false)
    private String start_date;

    @OneToOne
    @JoinColumn(name = "carid", referencedColumnName = "id", nullable = false)
    private Car car;
}
