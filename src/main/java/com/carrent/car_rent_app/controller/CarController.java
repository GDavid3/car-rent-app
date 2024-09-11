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

@Controller
@RequestMapping("/cars") //HTTP Post request from url
public class CarController {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private OrderRepo orderRepo;

    //read from DB
    @GetMapping({"", "/"})
    public String showAvailableCarList(@RequestParam("rentstart") String start, @RequestParam("rentend") String end, Model model)
    {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate)+1;

        List<Order> filteredOrders = orderRepo.findAll().stream()
                .filter(x -> !(startDate.isAfter(LocalDate.parse(x.getStart_date()).plusDays(x.getDay_count()-1))
                || endDate.isBefore(LocalDate.parse(x.getStart_date())))).toList();
        List<Integer> carids = filteredOrders.stream().map(x -> x.getCar().getId()).toList();
        List<Car> cars = carRepo.findAll().stream().filter(c -> !carids.contains(c.getId())).toList();

        model.addAttribute("cars", cars);
        model.addAttribute("startDate", startDate);
        model.addAttribute("daysBetween", daysBetween);

        return "cars/index";
    }
}
