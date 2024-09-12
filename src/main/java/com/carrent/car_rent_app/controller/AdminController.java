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
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping({"", "/"})
    public String showOrdersList(Model model)
    {
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);

        return "admin/OrderListAdminMain";
    }

    //Todo update
}
