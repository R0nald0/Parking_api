package com.dio.parking.exeption;


import com.dio.parking.model.Parking;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends  RuntimeException {

       public  ParkingNotFoundException(String id){
          super("Parking id not found " + id);
     }

       public  ParkingNotFoundException(Parking parking){
           super("Parking is Closed on " + parking.getExitDate());
       }
}
