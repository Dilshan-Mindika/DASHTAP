package com.dashtap.DASHTAP.controller;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.dto.SearchVehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicleDTOList = userService.getAllVehicles();
        return ResponseEntity.ok(vehicleDTOList);
    }

    @PostMapping("vehicle/book")
    public ResponseEntity<Void> bookAVehicle(@RequestBody BookAVehicleDTO bookAVehicleDTO) {
        boolean success = userService.bookAVehicle(bookAVehicleDTO);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("vehicle/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long vehicleId) {
        VehicleDTO vehicleDTO = userService.getVehicleById(vehicleId);
        if (vehicleDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(vehicleDTO);
    }

    @GetMapping("vehicle/bookings/{userId}")
    public ResponseEntity<List<BookAVehicleDTO>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getBookingsByUserId(userId));
    }

    @PostMapping("/vehicle/search")
    public ResponseEntity<?> searchVehicle(@RequestBody SearchVehicleDTO searchVehicleDTO) {
        return ResponseEntity.ok(userService.searchVehicle(searchVehicleDTO));
    }

}
