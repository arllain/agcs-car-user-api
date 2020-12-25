package com.arllain.agcscaruserapi.repository;

import com.arllain.agcscaruserapi.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arllain
 */
public interface CarRepository extends JpaRepository<Car, Long> {

}