package com.dashtap.DASHTAP.controller;

import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
