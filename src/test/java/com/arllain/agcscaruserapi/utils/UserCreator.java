package com.arllain.agcscaruserapi.utils;

import com.arllain.agcscaruserapi.domain.User;

import java.time.LocalDate;

public class UserCreator {

    public static User createValidUser() {
        return User.builder()
                .id(1L)
                .firstName("user")
                .lastName("user")
                .email("user@user.com")
                .birthday(LocalDate.of(1976, 12, 18))
                .login("user")
                .password("12345")
                .phone("123456789")
                .build();
    }

    public static User createUserToBeSaved() {
        return User.builder()
                .firstName("user")
                .lastName("user")
                .email("user@user.com")
                .birthday(LocalDate.of(1976, 12, 18))
                .login("user")
                .password("12345")
                .phone("123456789")
                .build();
    }

    public static User createValidUpdateUser() {
        return User.builder()
                .id(1L)
                .firstName("user")
                .lastName("user")
                .email("user@user.com")
                .birthday(LocalDate.of(1976, 12, 18))
                .login("user")
                .password("12345")
                .phone("123456789")
                .build();
    }
}
