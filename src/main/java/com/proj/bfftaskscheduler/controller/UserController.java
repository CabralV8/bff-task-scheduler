package com.proj.bfftaskscheduler.controller;

import com.proj.bfftaskscheduler.business.UserService;
import com.proj.bfftaskscheduler.business.dto.in.AddressDTORequest;
import com.proj.bfftaskscheduler.business.dto.in.LoginRequest;
import com.proj.bfftaskscheduler.business.dto.in.TelephoneDTORequest;
import com.proj.bfftaskscheduler.business.dto.in.UserDTORequest;
import com.proj.bfftaskscheduler.business.dto.out.AddressDTOResponse;
import com.proj.bfftaskscheduler.business.dto.out.TelephoneDTOResponse;
import com.proj.bfftaskscheduler.business.dto.out.UserDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "Handles user registration and authentication")
public class  UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Register user", description = "Creates a new user.")
    @ApiResponse(responseCode = "200", description = "User successfully registered.")
    @ApiResponse(responseCode = "400", description = "User already registered.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<UserDTOResponse> saveUser(@RequestBody UserDTORequest userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user and returns a token.")
    @ApiResponse(responseCode = "200", description = "User successfully logged in.")
    @ApiResponse(responseCode = "401", description = "Invalid credentials.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public String login(@RequestBody LoginRequest userDTO) {
        return userService.loginUser(userDTO);
    }

    @GetMapping
    @Operation(summary = "Get user by email", description = "Retrieves user data by email.")
    @ApiResponse(responseCode = "200", description = "User successfully found.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<UserDTOResponse> searchUserByEmail(@RequestParam("email") String email,
                                                             @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.searchUserByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by email", description = "Deletes a user by email.")
    @ApiResponse(responseCode = "200", description = "User successfully deleted.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update user data", description = "Updates basic user information.")
    @ApiResponse(responseCode = "200", description = "User successfully updated.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<UserDTOResponse> updateUserData(@RequestBody UserDTORequest userDTO,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateUserData(token, userDTO));
    }

    @PutMapping("/address")
    @Operation(summary = "Update user address", description = "Updates the user's address.")
    @ApiResponse(responseCode = "200", description = "User address successfully updated.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestBody AddressDTORequest addressDTO,
                                                            @RequestParam("id") Long id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateAddress(id, addressDTO, token));
    }

    @PutMapping("/telephone")
    @Operation(summary = "Update user telephone", description = "Updates the user's telephone number.")
    @ApiResponse(responseCode = "200", description = "User telephone successfully updated.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<TelephoneDTOResponse> updateTelephone(@RequestBody TelephoneDTORequest telephoneDTO,
                                                                @RequestHeader("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateTelephone(id, telephoneDTO, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Register user address", description = "Registers a new address for the user.")
    @ApiResponse(responseCode = "200", description = "Address successfully registered.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<AddressDTOResponse> registerAddress(@RequestBody AddressDTORequest addressDTO,
                                                              @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.registerAddress(token, addressDTO));
    }

    @PostMapping("/telephone")
    @Operation(summary = "Register user telephone", description = "Registers a new telephone number for the user.")
    @ApiResponse(responseCode = "200", description = "Telephone successfully registered.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<TelephoneDTOResponse> registerTelephone(@RequestBody TelephoneDTORequest telephoneDTO,
                                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.registerTelephone(token, telephoneDTO));
    }
}
