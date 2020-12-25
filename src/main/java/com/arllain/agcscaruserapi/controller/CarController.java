package com.arllain.agcscaruserapi.controller;

import com.arllain.agcscaruserapi.domain.Car;
import com.arllain.agcscaruserapi.domain.User;
import com.arllain.agcscaruserapi.dto.*;
import com.arllain.agcscaruserapi.service.CarService;
import com.arllain.agcscaruserapi.service.UserService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author arllain
 */
@RestController
@RequestMapping(path = "/api")
@Api(tags = "cars")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(value = "/cars")
    @ApiOperation(value = "${CarController.listAll}", response = CarResponseDTO.class, authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Validation error"), //
            @ApiResponse(code = 401, message = "Unauthorized"), //
            @ApiResponse(code = 404, message = "User not found"),//
            @ApiResponse(code = 440, message = "Unauthorized - invalid session") })
    public ResponseEntity<List<CarResponseDTO>> listAll(HttpServletRequest req) {

        return  ResponseEntity.ok(carService.findAllByUser(userService.whoami(req))
                .stream()
                .map(car -> modelMapper.map(car, CarResponseDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping(path = "/cars")
    @ApiOperation(value = "${CarController.save}", response = CarResponseDTO.class, authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Validation error"), //
            @ApiResponse(code = 401, message = "Unauthorized"), //
            @ApiResponse(code = 422, message = "License plate already exists"),//
            @ApiResponse(code = 404, message = "User not found"),//
            @ApiResponse(code = 440, message = "Unauthorized - invalid session") })
    public ResponseEntity<CarResponseDTO> save(@ApiParam("Car Save") HttpServletRequest req,
                                       @Valid @RequestBody CarDTO carDTO) {
        carDTO.setUser(userService.whoami(req));
        return ResponseEntity.ok(modelMapper.map(
                carService.save(modelMapper.map(carDTO, Car.class)), CarResponseDTO.class));
    }

    @GetMapping(path = "/cars/{id}")
    @ApiOperation(value = "${CarController.findbyId}", response = CarResponseDTO.class, authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Validation error"), //
            @ApiResponse(code = 401, message = "Unauthorized"), //
            @ApiResponse(code = 404, message = "Car not found"),//
            @ApiResponse(code = 440, message = "Unauthorized - invalid session") })
    public ResponseEntity<CarResponseDTO> findById(@ApiParam("id") HttpServletRequest req,
                                                   @PathVariable long id) {
        return ResponseEntity.ok(modelMapper.map(
                carService.findById(id, userService.whoami(req)), CarResponseDTO.class));
    }

    @DeleteMapping(path = "/cars/{id}")
    @ApiOperation(value = "${CarController.delete}", response = Void.class, authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Validation error"), //
            @ApiResponse(code = 401, message = "Unauthorized"), //
            @ApiResponse(code = 404, message = "Car not found"),//
            @ApiResponse(code = 440, message = "Unauthorized - invalid session") })
    public ResponseEntity<Void> delete(@ApiParam("id") HttpServletRequest req,
                                                   @PathVariable long id) {
        carService.delete(id, userService.whoami(req));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/cars/{id}")
    @ApiOperation(value = "${CarController.update}", response = Void.class, authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Validation error"), //
            @ApiResponse(code = 401, message = "Unauthorized"), //
            @ApiResponse(code = 404, message = "Car not found"),//
            @ApiResponse(code = 440, message = "Unauthorized - invalid session") })
    public ResponseEntity<Void> update(@ApiParam("id") HttpServletRequest req,
                                       @PathVariable long id, @Valid @RequestBody CarUpdateDTO carUpdateDTO) {
        carUpdateDTO.setUser(userService.whoami(req));
        carService.update(id, modelMapper.map(carUpdateDTO, Car.class));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
