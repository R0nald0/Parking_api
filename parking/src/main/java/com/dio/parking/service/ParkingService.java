package com.dio.parking.service;

import com.dio.parking.model.Parking;
import com.dio.parking.repository.ParkingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ParkingService {

    final ParkingRepository parkingRepository;

    @Transactional
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }
}
