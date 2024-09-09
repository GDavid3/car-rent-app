package com.carrent.car_rent_app.controller;


import com.carrent.car_rent_app.model.Car;
import com.carrent.car_rent_app.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepo repo;

    //read from DB
    @GetMapping({"", "/"})
    public String showCarList(Model model)
    {
        System.out.println(model);
        List<Car> cars = repo.findAll();
        model.addAttribute("cars", cars);
        return "cars/index";
    }
}
