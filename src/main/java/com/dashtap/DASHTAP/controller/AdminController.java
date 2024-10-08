package com.dashtap.DASHTAP.controller;

import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor

public class AdminController {

    private final AdminService adminService;

    @PostMapping("/vehicle")
    public ResponseEntity<?> postVehicle(@ModelAttribute VehicleDTO vehicleDTO) throws IOException {
        boolean success = adminService.postVehicle(vehicleDTO);
        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
