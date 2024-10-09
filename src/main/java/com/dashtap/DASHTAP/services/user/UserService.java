package com.dashtap.DASHTAP.services.user;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.dto.SearchVehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTOListDTO;

import java.util.List;

public interface UserService {

    List<VehicleDTO> getAllVehicles();

    boolean bookAVehicle(BookAVehicleDTO bookAVehicleDTO);

    VehicleDTO getVehicleById(Long vehicleId);

    List<BookAVehicleDTO> getBookingsByUserId(Long userId);

    VehicleDTOListDTO searchVehicle(SearchVehicleDTO searchVehicleDTO);

}
