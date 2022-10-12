package com.dio.parking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ParkingCreateDto {
    private String license;
    private String state;
    private String model;
    private String color;
}
