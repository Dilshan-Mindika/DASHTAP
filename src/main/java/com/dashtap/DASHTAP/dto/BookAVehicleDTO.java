package com.dashtap.DASHTAP.dto;

import com.dashtap.DASHTAP.enums.BookVehicleStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookAVehicleDTO {

    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long price;

    private BookVehicleStatus bookVehicleStatus;

    private Long vehicleId;

    private Long userId;

}
