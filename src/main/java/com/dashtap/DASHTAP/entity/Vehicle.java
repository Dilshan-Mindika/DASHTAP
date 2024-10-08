package com.dashtap.DASHTAP.entity;

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

    private String type;

    private String regNumber;

    private String color;

    private String transmission;

    private String fuelType;

    private Long rate;

    private String description;

    private Date year;

    private byte[] image;

}
