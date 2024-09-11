package com.carrent.car_rent_app.model;

import jakarta.validation.constraints.*;

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

    public @NotBlank(message = "The name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "The name is required") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "The address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotEmpty(message = "The address is required") String address) {
        this.address = address;
    }

    public @NotEmpty(message = "The email is required") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "The email is required") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "The phone number is required") String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(@NotEmpty(message = "The phone number is required") String phone_number) {
        this.phone_number = phone_number;
    }

    @Min(1)
    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(@Min(1) int day_count) {
        this.day_count = day_count;
    }

    public @NotEmpty(message = "The phone number is required") String getStart_date() {
        return start_date;
    }

    public void setStart_date(@NotEmpty(message = "The phone number is required") String start_date) {
        this.start_date = start_date;
    }

    public @NotEmpty(message = "The full price is required") String getFull_price() {
        return full_price;
    }

    public void setFull_price(@NotEmpty(message = "The full price is required") String full_price) {
        this.full_price = full_price;
    }

    @Min(1)
    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(@Min(1) int car_id) {
        this.car_id = car_id;
    }
}
