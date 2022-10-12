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

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findOne(@PathVariable String id){
               Parking parking =parkingService.findById(id);
               ParkingDTO parkingDTO = parkingMapper.convertToParkingDto(parking);
          return ResponseEntity.ok(parkingDTO);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> insertParking(@RequestBody ParkingCreateDto parkingCreateDto){
         ParkingDTO parkingDTO = parkingMapper.convertToParkingDto(parkingCreateDto);
            parkingService.insertPaking(parkingDTO);
          return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<ParkingDTO> upadate(@PathVariable String id , @RequestBody ParkingCreateDto parkingCreateDto){

        ParkingDTO parkingDTO = parkingMapper.convertToParkingDto(parkingCreateDto);
        parkingService.updateParking( id ,parkingDTO);
        return ResponseEntity.ok(parkingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParking(@PathVariable String id){
          parkingService.deleteParking(id);
          return  ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/exit")
    public  ResponseEntity<ParkingDTO> checkOut(@PathVariable String id){
                Parking  parking= parkingService.checkOut(id);
           ParkingDTO parkingDTO = parkingMapper.convertToParkingDto(parking);
           return ResponseEntity.ok(parkingDTO);
    }





}
