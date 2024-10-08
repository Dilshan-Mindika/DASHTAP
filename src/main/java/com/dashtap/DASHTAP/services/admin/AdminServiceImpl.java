package com.dashtap.DASHTAP.services.admin;

import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.entity.Vehicle;
import com.dashtap.DASHTAP.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService {

    private final VehicleRepository vehicleRepository;

    @Override
    public boolean postVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setName(vehicleDTO.getName());
        vehicle.setOwner(vehicleDTO.getOwner());
        vehicle.setOwnerNumber(vehicleDTO.getOwnerNumber());
        vehicle.setRate(vehicleDTO.getRate());
        vehicle.setDescription(vehicleDTO.getDescription());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setImage(vehicleDTO.getImage());

        vehicleRepository.save(vehicle);
        return false;
    }
}
