package com.example.demo.timur.controller;

import com.example.demo.timur.controller.Exception.ErrorResponse;
import com.example.demo.timur.controller.Exception.ThemeParkRideException;
import com.example.demo.timur.entity.ThemeParkRide;
import com.example.demo.timur.repository.ThemeParkRideRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ThemeParkRideController {
    private final ThemeParkRideRepository themeParkRideRepository;
    //Cat cat = new Cat();

    public ThemeParkRideController(ThemeParkRideRepository themeParkRideRepository) {
        this.themeParkRideRepository = themeParkRideRepository;
    }

    @Operation(summary = "Get a all rides")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All rides",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ThemeParkRide.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content =@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ride not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    }) @GetMapping(value = "/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ThemeParkRide> getRides() {
        return themeParkRideRepository.findAll();
    }

    @Operation(summary = "Get a ride by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ride",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ThemeParkRide.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content =@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ride not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    }) @GetMapping(value = "/ride/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ThemeParkRide getRide(@PathVariable long id){
        return themeParkRideRepository.findById(id).orElseThrow(() -> new ThemeParkRideException( String.format("Invalid ride id %s", id)));
    }

    @Operation(summary = "Get a ride by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ride",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ThemeParkRide.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content =@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ride not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    }) @GetMapping(value = "/rideex/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ThemeParkRide getRideEx(@PathVariable long id){
        return themeParkRideRepository.findById(id).orElseThrow(() -> new ThemeParkRideException("hello world!"));
    }


    @Operation(summary = "Post ride")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post the ride",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ThemeParkRide.class)) }),
            @ApiResponse(responseCode = "500", description = "Something ride wrong",
                    content =@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ride with simple id",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    }) @PostMapping(value = "/ride", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ThemeParkRide createRide(@Valid @RequestBody ThemeParkRide themeParkRide) {
        return themeParkRideRepository.save(themeParkRide);
    }

}