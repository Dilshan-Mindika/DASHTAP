package com.dashtap.DASHTAP.entity;

import com.dashtap.DASHTAP.enums.BookVehicleStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
public class BookAVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    @Transient
    public Long getDays() {
        if (fromDate != null && toDate != null) {
            return ChronoUnit.DAYS.between(fromDate, toDate);
        }
        return null;
    }

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
}
