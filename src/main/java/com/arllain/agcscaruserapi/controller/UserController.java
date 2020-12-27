package com.arllain.agcscaruserapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.arllain.agcscaruserapi.domain.User;
import com.arllain.agcscaruserapi.dto.UserCredentialsDTO;
import com.arllain.agcscaruserapi.dto.UserResponseDTO;
import com.arllain.agcscaruserapi.dto.UserSignInDTO;
import com.arllain.agcscaruserapi.service.UserService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arllain.agcscaruserapi.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author arllain
 *
 */

@RestController
@RequestMapping(path = "/api")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Validation error"), //
            @ApiResponse(code = 401, message = "Invalid login or password") })
    public ResponseEntity<UserCredentialsDTO> signin(@ApiParam("User SignIn") @Valid @RequestBody UserSignInDTO user) {
        return ResponseEntity.ok(userService.signIn(modelMapper.map(user, User.class)));
    }

    @GetMapping(path = "/users")
    @ApiOperation(value = "${UserController.listAll}", response = UserResponseDTO.class)
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        return  ResponseEntity.ok(userService.listAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/users/{id}")
    @ApiOperation(value = "${UserController.findById}", response = UserResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<UserResponseDTO> findById(@ApiParam("id") @PathVariable long id) {
        return ResponseEntity.ok(modelMapper.map(userService.findById(id), UserResponseDTO.class));
    }

    @PostMapping(path = "/users")
    @ApiOperation(value = "${UserController.save}")
    @ApiResponses(value = { //
            @ApiResponse(code = 422, message = "E-mail already exists"),//
            @ApiResponse(code = 422, message = "Login already exists"), //
            @ApiResponse(code = 400, message = "Validation error"), //
            })
    public ResponseEntity<String> save(@ApiParam("User Signup") @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(modelMapper.map(userDTO, User.class)));
    }

    @DeleteMapping(path = "/users/{id}")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<Void> delete(@ApiParam("id") @PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/users/{id}")
    @ApiOperation(value = "${UserController.update}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<Void> update(@ApiParam("id") @PathVariable long id,
                                       @Valid @RequestBody UserDTO userDTO) {
        userService.update(id, modelMapper.map(userDTO, User.class));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/me")
    @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class, authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Validation error"), //
            @ApiResponse(code = 401, message = "Unauthorized"), //
            @ApiResponse(code = 440, message = "Unauthorized - invalid session") })
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }
}
