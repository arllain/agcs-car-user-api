package com.arllain.agcscaruserapi.service;

import com.arllain.agcscaruserapi.domain.Car;
import com.arllain.agcscaruserapi.domain.User;
import com.arllain.agcscaruserapi.repository.CarRepository;
import com.arllain.agcscaruserapi.repository.UserRepository;
import com.arllain.agcscaruserapi.service.exception.ObjectFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * @author arllain
 *
 */
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return List<Car>
     * @param user
     */
    public List<Car> findAllByUser(User user) {
        return carRepository.findAllByUser(user);
    }

    /**
     *
     * @param car
     * @return
     */
    @Transactional
    public Car save(Car car) {
        if (carRepository.findByUserAndLicensePlate(car.getUser(), car.getLicensePlate()) != null){
            throw new ObjectFoundException("License plate already exists");
        }

        carRepository.save(car);
        return car;
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    public Car findById(long id, User user) {
        return carRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ObjectFoundException("Car not found"));
    }

    /**
     *
     * @param id
     * @param user
     */
    @Transactional
    public void delete(long id, User user) {
        Car car = findById(id, user);
        car.setUser(null);
        carRepository.delete(car);
    }

    /**
     *
     * @param car
     */
    public void update(long id, Car car) {
        Car savedCar = findById(id, car.getUser());
        car.setId(savedCar.getId());
        carRepository.save(car);
    }
}
