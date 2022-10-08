package com.dio.parking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingCreateDto {
    private String license;
    private String state;
    private String model;
    private String color;
}
