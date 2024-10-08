package com.dashtap.DASHTAP.services.admin;

import com.dashtap.DASHTAP.dto.VehicleDTO;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postVehicle(VehicleDTO vehicleDTO) throws IOException;

    List<VehicleDTO> getAllVehicles();

    void deleteVehicle(Long id);

}
