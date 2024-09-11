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
    private OrderRepo orepo;

    @Autowired
    private CarRepo crepo;

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

        Order order = new Order();
        order.setName(orderDto.getName());
        order.setAddress(orderDto.getAddress());
        order.setEmail(orderDto.getEmail());
        order.setPhone_number(orderDto.getPhone_number());
        order.setDay_count(orderDto.getDay_count());
        order.setStart_date(orderDto.getStart_date());
        order.setCar(crepo.findById(orderDto.getCar_id()));

        orepo.save(order);

        return "redirect:/";
    }
}
