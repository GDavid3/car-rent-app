package com.carrent.car_rent_app.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private MultipartFile image;

    @Min(1)
    private int daily_price;

    private boolean active;
}