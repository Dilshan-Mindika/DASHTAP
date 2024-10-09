package com.dashtap.DASHTAP.repository;

import com.dashtap.DASHTAP.entity.BookAVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookAVehicleRepository extends JpaRepository<BookAVehicle, Long> {
}
