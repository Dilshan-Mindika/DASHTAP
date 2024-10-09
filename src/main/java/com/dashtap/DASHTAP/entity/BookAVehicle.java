package com.dashtap.DASHTAP.entity;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.enums.BookVehicleStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Data
public class BookAVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    @Enumerated(EnumType.STRING)
    private BookVehicleStatus bookVehicleStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Vehicle vehicle;

    public BookAVehicleDTO getBookAVehicleDTO() {
        BookAVehicleDTO bookAVehicleDTO = new BookAVehicleDTO();
        bookAVehicleDTO.setId(id);
        bookAVehicleDTO.setFromDate(fromDate);
        bookAVehicleDTO.setToDate(toDate);
        bookAVehicleDTO.setPrice(price);
        bookAVehicleDTO.setBookVehicleStatus(bookVehicleStatus);
        bookAVehicleDTO.setUserId(user.getId());
        bookAVehicleDTO.setVehicleId(vehicle.getId());
        return bookAVehicleDTO;
    }
}
