package com.arllain.agcscaruserapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0)
    @NotEmpty(message = "Login is required")
    private String login;
    @ApiModelProperty(position = 1)
    @NotEmpty(message = "Password is required")
    private String password;

}
