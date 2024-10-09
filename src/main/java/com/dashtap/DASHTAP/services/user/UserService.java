package com.dashtap.DASHTAP.services.user;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTO;

import java.util.List;

public interface UserService {

    List<VehicleDTO> getAllVehicles();

    boolean bookVehicle(BookAVehicleDTO bookAVehicleDTO);

}
