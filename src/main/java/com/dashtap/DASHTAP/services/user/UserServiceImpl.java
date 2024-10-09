package com.dashtap.DASHTAP.services.user;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.dto.SearchVehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTOListDTO;
import com.dashtap.DASHTAP.entity.BookAVehicle;
import com.dashtap.DASHTAP.entity.User;
import com.dashtap.DASHTAP.entity.Vehicle;
import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.enums.BookVehicleStatus;
import com.dashtap.DASHTAP.repository.BookAVehicleRepository;
import com.dashtap.DASHTAP.repository.UserRepository;
import com.dashtap.DASHTAP.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service("userServiceImpl2")
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    private final BookAVehicleRepository bookAVehicleRepository;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(Vehicle::getVehicleDTO).collect(Collectors.toList());
    }

    @Override
    public boolean bookAVehicle(BookAVehicleDTO bookAVehicleDTO) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(bookAVehicleDTO.getVehicleId());
        Optional<User> optionalUser = userRepository.findById(bookAVehicleDTO.getUserId());
        if (optionalVehicle.isPresent() && optionalUser.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            BookAVehicle bookAVehicle = new BookAVehicle();
            bookAVehicle.setVehicle(existingVehicle);
            bookAVehicle.setBookVehicleStatus(BookVehicleStatus.Pending);
            long diffInMilliSeconds = bookAVehicleDTO.getToDate().getTime() - bookAVehicleDTO.getFromDate().getTime();
            long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
            bookAVehicle.setPrice(days * existingVehicle.getRate());
            bookAVehicleRepository.save(bookAVehicle);
            return true;
        }
        return false;
    }

    @Override
    public VehicleDTO getVehicleById(Long vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        return optionalVehicle.map(Vehicle::getVehicleDTO).orElse(null);
    }

    @Override
    public List<BookAVehicleDTO> getBookingsByUserId(Long userId) {
        return bookAVehicleRepository.findAllByUserId(userId); //error occurs on here[.stream().map(BookAVehicle::getBookAVehicleDTO).collect(Collectors.toList()]
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
