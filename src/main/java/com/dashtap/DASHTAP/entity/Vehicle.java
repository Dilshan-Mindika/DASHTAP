package com.dashtap.DASHTAP.entity;

import com.dashtap.DASHTAP.dto.VehicleDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name= "vehicle")

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String name;

    private String owner;

    private Long ownerNumber;

    private String type;

    private String regNumber;

    private String color;

    private String transmission;

    private String fuelType;

    private Long rate;

    private String description;

    private Date year;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public VehicleDTO getVehicleDTO() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(id);
        vehicleDTO.setBrand(brand);
        vehicleDTO.setName(name);
        vehicleDTO.setOwner(owner);
        vehicleDTO.setOwnerNumber(ownerNumber);
        vehicleDTO.setType(type);
        vehicleDTO.setRegNumber(regNumber);
        vehicleDTO.setColor(color);
        vehicleDTO.setTransmission(transmission);
        vehicleDTO.setFuelType(fuelType);
        vehicleDTO.setRate(rate);
        vehicleDTO.setDescription(description);
        vehicleDTO.setYear(year);
        vehicleDTO.setReturnedImage(image);
        return vehicleDTO;
    }

}
