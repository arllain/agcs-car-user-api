package com.arllain.agcscaruserapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsDTO implements Serializable {

    private UserDataDTO user;

    private String token = "";
}
