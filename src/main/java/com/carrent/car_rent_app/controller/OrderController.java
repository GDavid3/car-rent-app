package com.carrent.car_rent_app.controller;

import com.carrent.car_rent_app.model.Order;
import com.carrent.car_rent_app.model.OrderDto;
import com.carrent.car_rent_app.repo.CarRepo;
import com.carrent.car_rent_app.repo.OrderRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping("/make")
    public String showRentPage(Model model, @RequestParam int id, @RequestParam int daily_price, @RequestParam String startDate, @RequestParam int daysBetween){
        OrderDto orderDto = new OrderDto();
        int fullPrice = daily_price * daysBetween;
        orderDto.setDay_count(daysBetween);
        orderDto.setStart_date(startDate);
        orderDto.setCar_id(id);
        orderDto.setFull_price(String.valueOf(fullPrice) + "$");

        model.addAttribute("orderDto", orderDto);

        return  "orders/MakeOrder";
    }

    @PostMapping("/make")
    public String makeOrder(@Valid @ModelAttribute OrderDto orderDto, BindingResult result){
        if (result.hasErrors())
        {
            return "orders/MakeOrder";
        }

        Order order = Order.builder().name(orderDto.getName()).address(orderDto.getAddress()).email(orderDto.getEmail()).
                phone_number(orderDto.getPhone_number()).day_count(orderDto.getDay_count()).start_date(orderDto.getStart_date()).
                car(carRepo.findById(orderDto.getCar_id())).build();

        orderRepo.save(order);

        return "redirect:/";
    }
}
