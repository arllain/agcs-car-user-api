package com.arllain.agcscaruserapi.dto;

import com.arllain.agcscaruserapi.domain.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0)
    @NotEmpty(message = "year is required")
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
}
