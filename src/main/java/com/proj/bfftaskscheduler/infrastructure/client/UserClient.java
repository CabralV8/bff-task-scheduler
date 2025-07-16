package com.proj.bfftaskscheduler.infrastructure.client;


import com.proj.bfftaskscheduler.business.dto.in.AddressDTORequest;
import com.proj.bfftaskscheduler.business.dto.in.LoginRequest;
import com.proj.bfftaskscheduler.business.dto.in.TelephoneDTORequest;
import com.proj.bfftaskscheduler.business.dto.in.UserDTORequest;
import com.proj.bfftaskscheduler.business.dto.out.AddressDTOResponse;
import com.proj.bfftaskscheduler.business.dto.out.TelephoneDTOResponse;
import com.proj.bfftaskscheduler.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTOResponse searchUserByEmail(@RequestParam("email") String email,
                                      @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTOResponse saveUser(@RequestBody UserDTORequest userDTO);

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest userDTO);


    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email,
                           @RequestHeader("Authorization") String token);


    @PutMapping
    UserDTOResponse updateUserData(@RequestBody UserDTORequest userDTO,
                                   @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest addressDTO,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/telephone")
    TelephoneDTOResponse updateTelephone(@RequestBody TelephoneDTORequest telephoneDTO,
                                         @RequestHeader("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDTOResponse registerAddres(@RequestBody AddressDTORequest addressDTO,
                                      @RequestHeader("Authorization") String token);

    @PostMapping("/telphone")
    TelephoneDTOResponse registerTelephone(@RequestBody TelephoneDTORequest telephoneDTO,
                                           @RequestHeader("Authorization") String token);

}