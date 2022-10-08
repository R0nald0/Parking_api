package com.dio.parking.service;

import com.dio.parking.controllers.mapper.ParkingMapper;
import com.dio.parking.exeption.ParkingNotFoundException;
import com.dio.parking.model.Parking;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dio.parking.model.dto.ParkingDTO;
import com.dio.parking.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    final ParkingRepository parkingRepository;
    final ParkingMapper parkingMapper;


    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }
   @Transactional(readOnly = true)
    public Parking findById(String id){
            return parkingRepository.findById(id).orElseThrow(
                    ()->{
                      throw   new ParkingNotFoundException(id);
                    }
            );
    }

    @Transactional
    public void insertPaking(ParkingDTO parkingDto) {

        Parking parking = parkingMapper.covertToParking(parkingDto);
        String uuid = getUUid();
        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parking);

    }
    private static String getUUid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
