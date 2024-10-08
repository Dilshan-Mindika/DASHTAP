package com.dashtap.DASHTAP.services.admin;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.dto.SearchVehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTOListDTO;
import com.dashtap.DASHTAP.entity.BookAVehicle;
import com.dashtap.DASHTAP.entity.Vehicle;
import com.dashtap.DASHTAP.enums.BookVehicleStatus;
import com.dashtap.DASHTAP.repository.BookAVehicleRepository;
import com.dashtap.DASHTAP.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService {

    private final VehicleRepository vehicleRepository;

    private final BookAVehicleRepository bookAVehicleRepository;

    @Override
    public boolean postVehicle(VehicleDTO vehicleDTO) throws IOException {
        try{
            Vehicle vehicle = new Vehicle();
            vehicle.setBrand(vehicleDTO.getBrand());
            vehicle.setName(vehicleDTO.getName());
            vehicle.setBrand(vehicleDTO.getBrand());
            vehicle.setOwner(vehicleDTO.getOwner());
            vehicle.setOwnerNumber(vehicleDTO.getOwnerNumber());
            vehicle.setType(vehicleDTO.getType());
            vehicle.setRegNumber(vehicleDTO.getRegNumber());
            vehicle.setColor(vehicleDTO.getColor());
            vehicle.setTransmission(vehicleDTO.getTransmission());
            vehicle.setFuelType(vehicleDTO.getFuelType());
            vehicle.setRate(vehicleDTO.getRate());
            vehicle.setDescription(vehicleDTO.getDescription());
            vehicle.setYear(vehicleDTO.getYear());
            vehicle.setImage(vehicleDTO.getImage().getBytes());
            vehicleRepository.save(vehicle);
            return true;
    }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(Vehicle::getVehicleDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        return optionalVehicle.map(Vehicle::getVehicleDTO).orElse(null);
    }

    @Override
    public boolean updateVehicle(Long vehicleId, VehicleDTO vehicleDTO) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();

            if (vehicleDTO.getImage() != null) {
                try {
                    existingVehicle.setImage(vehicleDTO.getImage().getBytes());
                } catch (IOException e) {
                    return false;
                }
            }

            existingVehicle.setBrand(vehicleDTO.getBrand());
            existingVehicle.setName(vehicleDTO.getName());
            existingVehicle.setDescription(vehicleDTO.getDescription());
            existingVehicle.setColor(vehicleDTO.getColor());
            existingVehicle.setFuelType(vehicleDTO.getFuelType());
            existingVehicle.setOwnerNumber(vehicleDTO.getOwnerNumber());
            existingVehicle.setOwner(vehicleDTO.getOwner());
            existingVehicle.setRate(vehicleDTO.getRate());
            existingVehicle.setRegNumber(vehicleDTO.getRegNumber());
            existingVehicle.setTransmission(vehicleDTO.getTransmission());
            existingVehicle.setYear(vehicleDTO.getYear());
            existingVehicle.setType(vehicleDTO.getType());
            vehicleRepository.save(existingVehicle);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BookAVehicleDTO> getBookings() {
        return bookAVehicleRepository.findAll().stream().map(BookAVehicle::getBookAVehicleDTO).collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookAVehicle> optionalBookAVehicle = bookAVehicleRepository.findById(bookingId);
        if (optionalBookAVehicle.isPresent()) {
            BookAVehicle exsistingBookAVehicle = optionalBookAVehicle.get();
            if (Objects.equals(status, "Approve"))
                exsistingBookAVehicle.setBookVehicleStatus(BookVehicleStatus.Approved);
            else
                exsistingBookAVehicle.setBookVehicleStatus(BookVehicleStatus.Rejected);
            bookAVehicleRepository.save(exsistingBookAVehicle);
            return true;
            }
        return false;
    }

    @Override
    public VehicleDTOListDTO searchVehicle(SearchVehicleDTO searchVehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(searchVehicleDTO.getBrand());
        vehicle.setType(searchVehicleDTO.getType());
        vehicle.setFuelType(searchVehicleDTO.getFuelType());
        vehicle.setColor(searchVehicleDTO.getColor());
        vehicle.setTransmission(searchVehicleDTO.getTransmission());
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("fuelType", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Vehicle> vehicleExample = Example.of(vehicle, exampleMatcher);
        List<Vehicle> vehicleList = vehicleRepository.findAll(vehicleExample);
        VehicleDTOListDTO vehicleDTOListDTO = new VehicleDTOListDTO();
        vehicleDTOListDTO.setVehicleDTOList(vehicleList.stream().map(Vehicle::getVehicleDTO).collect(Collectors.toList()));
        return vehicleDTOListDTO;
    }

}

