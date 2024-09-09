package com.carrent.car_rent_app.controller;


import com.carrent.car_rent_app.model.Car;
import com.carrent.car_rent_app.model.Order;
import com.carrent.car_rent_app.repo.CarRepo;
import com.carrent.car_rent_app.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars") //HTTP Post request from url
public class CarController {
    @Autowired
    private CarRepo crepo;

    @Autowired
    private OrderRepo orepo;

    //read from DB
    @GetMapping({"", "/"})
    public String showCarList(@RequestParam("rentstart") String start, @RequestParam("rentend") String end, Model model)
    {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate)+1;
        System.out.println(daysBetween);
        List<Order> filteredOrders = orepo.findAll().stream()
                .filter(x -> !(startDate.isAfter(LocalDate.parse(x.getStart_date()).plusDays(x.getDay_count()-1))
                || endDate.isBefore(LocalDate.parse(x.getStart_date())))).toList();
        List<Long> carids = filteredOrders.stream().map(x -> x.getCar().getId()).toList();
        List<Car> cars = crepo.findAll().stream().filter(c -> !carids.contains(c.getId())).toList();
        model.addAttribute("cars", cars);
        return "cars/index";
    }
}
