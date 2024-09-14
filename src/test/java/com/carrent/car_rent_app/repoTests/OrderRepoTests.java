package com.carrent.car_rent_app.repoTests;

import com.carrent.car_rent_app.model.*;
import com.carrent.car_rent_app.repo.CarRepo;
import com.carrent.car_rent_app.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepoTests {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CarRepo carRepo;

    @Test
    public void OrderRepo_SaveTest(){
        Car car = Car.getCarBuild("123.jpg",500);
        carRepo.save(car);
        Order order = Order.getOrderBuild("Daniel","TB street 12",
                "ab100@gmail.com","+213 586 210",1,"2024-09-29", car);

        Order savedOrder = orderRepo.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isGreaterThan(0);
    }

    @Test
    public void OrderRepo_FindAllTest()
    {
        Car car1 = Car.getCarBuild("123.jpg",500);
        carRepo.save(car1);
        Order order1 = Order.getOrderBuild("Daniel","TB street 12",
                "ab100@gmail.com","+213 586 210",3,"2024-09-25", car1);

        Car car2 = Car.getCarBuild("456.jpg",300);
        carRepo.save(car2);
        Order order2 = Order.getOrderBuild("Daniel","TB street 12",
                "ab100@gmail.com","+213 586 210",1,"2024-09-29", car2);

        orderRepo.save(order1);
        orderRepo.save(order2);

        List<Order> orderList = orderRepo.findAll();

        assertThat(orderList).isNotNull();
        assertThat(orderList).size().isEqualTo(2);
    }
}
