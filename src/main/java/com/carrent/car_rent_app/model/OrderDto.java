package com.carrent.car_rent_app.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotBlank(message = "The name is required")
    private String name;

    @NotEmpty(message = "The address is required")
    private String address;

    @NotEmpty(message = "The email is required")
    private String email;

    @NotEmpty(message = "The phone number is required")
    private String phone_number;

    @Min(1)
    private int day_count;

    @NotEmpty(message = "The phone number is required")
    private String start_date;

    @NotEmpty(message = "The full price is required")
    private String full_price;

    @Min(1)
    private int car_id;
}
