package com.arllain.agcscaruserapi.utils;

import com.arllain.agcscaruserapi.domain.Car;
import com.arllain.agcscaruserapi.domain.User;

import java.time.LocalDate;

public class CartCreator {

    public static Car createValidCar() {
        return Car.builder()
                .id(1L)
                .year(2022)
                .licensePlate("OXY-0000")
                .model("model")
                .color("color")
                .user(UserCreator.createValidUser())
                .build();
    }

    public static Car createCarToBeSaved() {
        return Car.builder()
                .year(2022)
                .licensePlate("OXY-0000")
                .model("model")
                .color("color")
                .user(UserCreator.createValidUser())
                .build();
    }

    public static Car createValidUpdateCar() {
        return Car.builder()
                .id(1L)
                .year(2022)
                .licensePlate("OXY-0000")
                .model("model")
                .color("color")
                .user(UserCreator.createValidUser())
                .build();
    }
}
