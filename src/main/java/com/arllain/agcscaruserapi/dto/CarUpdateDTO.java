package com.arllain.agcscaruserapi.dto;

import com.arllain.agcscaruserapi.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0)
    @NotNull(message = "year is required")
    private Integer year;

    @ApiModelProperty(position = 1)
    @NotEmpty(message = "License Plate name is required")
    private String licensePlate;

    @ApiModelProperty(position = 2)
    @NotEmpty(message = "Model is required")
    private String model;

    @ApiModelProperty(position = 3)
    @NotEmpty(message = "Color is required")
    private String color;

    @ApiModelProperty(position = 4)
    private User user;
}
