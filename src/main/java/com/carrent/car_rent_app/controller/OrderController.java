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
        int fullPrice = daily_price * daysBetween;

        OrderDto orderDto = OrderDto.builder().day_count(daysBetween).start_date(startDate).
                car_id(id).full_price(String.valueOf(fullPrice) + "$").build();

        model.addAttribute("orderDto", orderDto);

        return  "orders/MakeOrder";
    }

    @PostMapping("/make")
    public String makeOrder(@Valid @ModelAttribute OrderDto orderDto, BindingResult result){
        if (result.hasErrors())
        {
            return "orders/MakeOrder";
        }

        Order order = Order.getOrderBuild(orderDto.getName(),orderDto.getAddress(), orderDto.getEmail(),
                orderDto.getPhone_number(), orderDto.getDay_count(), orderDto.getStart_date(), carRepo.findById(orderDto.getCar_id()));

        orderRepo.save(order);

        return "redirect:/";
    }
}
