package com.dashtap.DASHTAP.repository;

import com.dashtap.DASHTAP.dto.BookAVehicleDTO;
import com.dashtap.DASHTAP.entity.BookAVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BookAVehicleRepository extends JpaRepository<BookAVehicle, Long> {
    List<BookAVehicleDTO> findAllByUserId(Long userId);
}
