package com.arllain.agcscaruserapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String firstName;

    @ApiModelProperty(position = 2)
    private String lastName;

    @ApiModelProperty(position = 3)
    private String email;

    @ApiModelProperty(position = 4)
    private LocalDate birthday;

    @ApiModelProperty(position = 5)
    private String login;

    @ApiModelProperty(position = 6)
    private String phone;

    @ApiModelProperty(position = 7)
    private Instant created_at;

    @ApiModelProperty(position = 8)
    private Instant last_login;
}

