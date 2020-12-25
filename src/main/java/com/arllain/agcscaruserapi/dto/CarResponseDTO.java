package com.arllain.agcscaruserapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0)
    private Integer year;

    @ApiModelProperty(position = 1)
    private String licensePlate;

    @ApiModelProperty(position = 2)
    private String model;

    @ApiModelProperty(position = 3)
    private String color;
}
