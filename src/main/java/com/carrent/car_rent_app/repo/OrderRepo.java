package com.carrent.car_rent_app.repo;

import com.carrent.car_rent_app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
