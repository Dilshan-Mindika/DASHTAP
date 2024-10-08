package com.dashtap.DASHTAP.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data

public class VehicleDTO {

    private Long id;

    private String brand;

    private String name;

    private String owner;

    private String type;

    private String regNumber;

    private String color;

    private String transmission;

    private String fuelType;

    private Long rate;

    private String description;

    private Date year;

    private byte[] image;

    private MultipartFile returnedImage;

}
