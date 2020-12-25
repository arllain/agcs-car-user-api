package com.arllain.agcscaruserapi.repository;

import com.arllain.agcscaruserapi.domain.Car;
import com.arllain.agcscaruserapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author arllain
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     *
     * @param user
     * @return
     */
    List<Car> findAllByUser(User user);

    /**
     *
     * @param user
     * @param licensePlate
     * @return
     */
    Car findByUserAndLicensePlate(User user, String licensePlate);

    /**
     *
     * @param id
     * @param user
     * @return
     */
    Optional<Car> findByIdAndUser(long id, User user);

    /**
     *
     * @param id
     * @param user
     */
    void deleteByIdAndUser(long id, User user);
}