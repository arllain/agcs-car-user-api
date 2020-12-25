package com.arllain.agcscaruserapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.*;

import com.arllain.agcscaruserapi.domain.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0)
    @NotEmpty(message = "First name is required")
    private String firstName;

    @ApiModelProperty(position = 1)
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @ApiModelProperty(position = 2)
    @NotEmpty(message = "Email Address is required")
    @Email(message = "You must enter a valid email")
    private String email;

    @ApiModelProperty(position = 3)
    @NotNull(message = "Birthday is required")
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat()
    private LocalDate birthday;

    @ApiModelProperty(position = 4)
    @NotEmpty(message = "login is required")
    private String login;

    @ApiModelProperty(position = 5)
    @NotEmpty(message = "Password is required")
    private String password;

    @ApiModelProperty(position = 6)
    @NotEmpty(message = "Phone is required")
    private String phone;

    @ApiModelProperty(position = 7)
    private List<CarDataDTO> cars;
}
