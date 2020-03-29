package com.giffgaff.cars.repository;

import com.giffgaff.cars.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Car repository.
 *
 */
public interface CarRepository extends JpaRepository<Car, Long> {

}
