package com.carrent.car_rent_app.controllerTests;

import com.carrent.car_rent_app.controller.CarController;
import com.carrent.car_rent_app.repo.CarRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = CarController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CarControllerTests {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepo carRepo;*/


}
