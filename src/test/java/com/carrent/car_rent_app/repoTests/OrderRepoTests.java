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
        Car car = Car.builder().image("123.jpg").daily_price(500).active(true).build();
        carRepo.save(car);
        Order order = Order.builder().name("Daniel").address("TB street 12").email("ab100@gmail.com")
                .phone_number("+213 586 210").day_count(3).start_date("2024-09-25").car(car).build();

        Order savedOrder = orderRepo.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isGreaterThan(0);
    }

    @Test
    public void OrderRepo_FindAllTest()
    {
        Car car1 = Car.builder().image("123.jpg").daily_price(500).active(true).build();
        carRepo.save(car1);
        Order order1 = Order.builder().name("Daniel").address("TB street 12").email("ab100@gmail.com")
                .phone_number("+213 586 210").day_count(3).start_date("2024-09-25").car(car1).build();

        Car car2 = Car.builder().image("456.jpg").daily_price(300).active(true).build();
        carRepo.save(car2);
        Order order2 = Order.builder().name("Daniel").address("TB street 12").email("ab100@gmail.com")
                .phone_number("+213 586 210").day_count(1).start_date("2024-09-29").car(car2).build();

        orderRepo.save(order1);
        orderRepo.save(order2);

        List<Order> orderList = orderRepo.findAll();

        assertThat(orderList).isNotNull();
        assertThat(orderList).size().isEqualTo(2);
    }
}
