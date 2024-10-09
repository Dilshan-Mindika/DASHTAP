package com.dashtap.DASHTAP.controller;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.dto.SearchVehicleDTO;
import com.dashtap.DASHTAP.dto.VehicleDTO;
import com.dashtap.DASHTAP.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/vehicles")
    public ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok(adminService.getAllVehicles());
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        adminService.deleteVehicle(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        VehicleDTO vehicleDTO = adminService.getVehicleById(id);
        return ResponseEntity.ok(vehicleDTO);
    }

    @PutMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Void> updateVehicle(@PathVariable Long vehicleId, @ModelAttribute VehicleDTO vehicleDTO) throws IOException {
        try {
            boolean success = adminService.updateVehicle(vehicleId, vehicleDTO);
            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/vehicle/bookings")
    public ResponseEntity<List<BookAVehicleDTO>> getBookings() {
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/vehicle/bookings/{bookingId}/{}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
        boolean success = adminService.changeBookingStatus(bookingId, status);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/vehicle/search")
    public ResponseEntity<?> searchVehicle(@RequestBody SearchVehicleDTO searchVehicleDTO) {
        return ResponseEntity.ok(adminService.searchVehicle(searchVehicleDTO));
    }

}
