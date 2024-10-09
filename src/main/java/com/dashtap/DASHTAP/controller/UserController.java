package com.dashtap.DASHTAP.controller;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
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

}
