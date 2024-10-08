package com.dashtap.DASHTAP.repository;

import com.dashtap.DASHTAP.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
