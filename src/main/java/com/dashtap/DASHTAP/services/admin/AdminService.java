package com.dashtap.DASHTAP.services.admin;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.entity.BookAVehicle;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postVehicle(VehicleDTO vehicleDTO) throws IOException;

    List<VehicleDTO> getAllVehicles();

    void deleteVehicle(Long id);

    VehicleDTO getVehicleById(Long id);

    boolean updateVehicle(Long vehicleId, VehicleDTO vehicleDTO);

    List<BookAVehicleDTO> getBookings();

    boolean changeBookingStatus(Long bookingId, String status);
}
