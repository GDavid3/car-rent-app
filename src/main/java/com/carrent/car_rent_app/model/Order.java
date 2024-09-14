package com.carrent.car_rent_app.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "carid", referencedColumnName = "id", nullable = false)
    private Car car;

    public static Order getOrderBuild(String name, String address, String email, String phone_number, int day_count, String start_date, Car car1) {
        return Order.builder().name(name).address(address).email(email)
                .phone_number(phone_number).day_count(day_count).start_date(start_date).car(car1).build();
    }
}
