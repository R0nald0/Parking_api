package com.dio.parking.controllers;


import com.dio.parking.controllers.mapper.ParkingMapper;
import com.dio.parking.model.Parking;
import com.dio.parking.model.dto.ParkingCreateDto;
import com.dio.parking.model.dto.ParkingDTO;
import com.dio.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequiredArgsConstructor
@Api(value = "/parking",tags = "Parking Controller")
@RestController
@RequestMapping(value = "/parking" )
public class ParkingController {

    final ParkingService parkingService;
    final ParkingMapper parkingMapper;

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){
          List<Parking> parkingList = parkingService.findAll();
          List<ParkingDTO> parkingDTOList = parkingMapper.toParkingDtoList(parkingList);

        return ResponseEntity.ok(parkingDTOList);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> insertParking(@RequestBody ParkingCreateDto parkingCreateDto){
         ParkingDTO parkingDTO = parkingMapper.convertToParkingDto(parkingCreateDto);
            parkingService.insertPaking(parkingDTO);
          return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
    }

}
