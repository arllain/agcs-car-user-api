package com.arllain.agcscaruserapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    @Size(min = 4, message = "Minimum login length: 4 characters")
    private String login;

    @JsonIgnore
    @Column(nullable = false)
    @Size(min = 6, message = "Minimum password length: 6 characters")
    private String password;

    @Column(nullable = false)
    @Size(min = 10, message = "Minimum phone length: 10 characters")
    private String phone;

    private Instant created_at;

    private Instant last_login;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> cars;

}
