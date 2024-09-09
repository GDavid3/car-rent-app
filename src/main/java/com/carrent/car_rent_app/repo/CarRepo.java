package com.carrent.car_rent_app.repo;

import com.carrent.car_rent_app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepo extends JpaRepository<Car, Long> {
}
