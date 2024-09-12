package com.carrent.car_rent_app.controller;


import com.carrent.car_rent_app.model.Car;
import com.carrent.car_rent_app.model.CarDto;
import com.carrent.car_rent_app.model.Order;
import com.carrent.car_rent_app.repo.CarRepo;
import com.carrent.car_rent_app.repo.OrderRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String showAvailableCarsList(@RequestParam("rentstart") String start, @RequestParam("rentend") String end, Model model)
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

        return "cars/CarList";
    }

    @GetMapping("/carmanage")
    public String showCarManagePage(Model model)
    {
        List<Car> cars = carRepo.findAll();
        model.addAttribute("cars", cars);
        return "cars/CarsManage";
    }

    @GetMapping("/add")
    public String showRentPage(Model model){
        CarDto carDto = new CarDto();

        model.addAttribute("carDto", carDto);

        return  "cars/AddCar";
    }

    @PostMapping("/add")
    public String addCar(@Valid @ModelAttribute CarDto carDto, BindingResult result){
        if (carDto.getImage().isEmpty())
        {
            result.addError(new FieldError("CarDto", "image", "The image file is required"));
        }

        if (result.hasErrors())
        {
            return "cars/AddCar";
        }

        MultipartFile image = carDto.getImage();
        String imageName = image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath))
            {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream())
            {
                Files.copy(inputStream, Paths.get(uploadDir + imageName), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Exception: " + ex.getMessage());
        }

        Car car = Car.builder().image(imageName).daily_price(carDto.getDaily_price()).active(true).build();

        carRepo.save(car);

        return "redirect:/admin";
    }
}
