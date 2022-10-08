package com.dio.parking.controllers.mapper;

import com.dio.parking.model.Parking;
import com.dio.parking.model.dto.ParkingCreateDto;
import com.dio.parking.model.dto.ParkingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private  static  final ModelMapper MODEL_MAPPER = new ModelMapper();


    //converte
    public  Parking covertToParking(ParkingDTO parkingDTO){
         return MODEL_MAPPER.map(parkingDTO,Parking.class);
    }

    public  ParkingDTO convertToParkingDto(ParkingCreateDto parkingCreateDto){
        return MODEL_MAPPER.map(parkingCreateDto,ParkingDTO.class);
    }

    //conversao de Parking para parkingDTO

    public  ParkingDTO convertToParkingDto(Parking parking){
        return MODEL_MAPPER.map(parking,ParkingDTO.class);
    }


     //Retorna Lista de DTOS ultilisando o metodo convertToParkingDto para criar cada Objeto e adiciona la lista

    public List<ParkingDTO> toParkingDtoList (List<Parking> parkingList){
         return parkingList.stream().map(this::convertToParkingDto).collect(Collectors.toList());
    }

}
