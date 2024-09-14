package com.carrent.car_rent_app.repoTests;

import com.carrent.car_rent_app.model.Car;
import com.carrent.car_rent_app.model.Order;
import com.carrent.car_rent_app.repo.CarRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarRepoTests {

    @Autowired
    private CarRepo carRepo;

    @Test
    public void OrderRepo_SaveTest(){
        Car car = Car.getCarBuild("123.jpg",500);

        Car savedCar = carRepo.save(car);

        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getId()).isGreaterThan(0);
    }

    @Test
    public void CarRepo_FindAllTest()
    {

        Car car1 = Car.getCarBuild("123.jpg",500);
        carRepo.save(car1);

        Car car2 = Car.getCarBuild("456.jpg",300);
        carRepo.save(car2);

        List<Car> carList = carRepo.findAll();

        assertThat(carList).isNotNull();
        assertThat(carList).size().isEqualTo(2);
    }

    @Test
    public void CarRepo_FindByIdTest()
    {
        Car car = Car.getCarBuild("123.jpg",500);

        carRepo.save(car);

        Car foundCar = carRepo.findById(car.getId());

        assertThat(foundCar).isNotNull();
    }
}
