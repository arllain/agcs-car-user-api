package com.arllain.agcscaruserapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 30, message = "Maximum first name length: 30 characters")
    private String firstName;

    @Column(nullable = false)
    @Size(max = 50, message = "Maximum last name length: 50 characters")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birthday;

    @Column(unique = true, nullable = false)
    @Size(min = 4, message = "Minimum login length: 4 characters")
    private String login;

    @JsonIgnore
    @Column(nullable = false)
    @Size(min = 5, message = "Minimum password length: 6 characters")
    private String password;

    @Column(nullable = false)
    @Size(min = 9, message = "Minimum phone length: 9 characters")
    private String phone;

    private Instant created_at;

    private Instant last_login;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Car> cars = new ArrayList<>();
}
