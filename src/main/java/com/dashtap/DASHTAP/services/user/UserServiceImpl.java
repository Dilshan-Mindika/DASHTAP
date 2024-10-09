package com.dashtap.DASHTAP.services.user;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.entity.Vehicle;
import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userServiceImpl2")
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(Vehicle::getVehicleDTO).collect(Collectors.toList());
    }

    @Override
    public boolean bookVehicle(BookAVehicleDTO bookAVehicleDTO) {
        return false;
    }

}
