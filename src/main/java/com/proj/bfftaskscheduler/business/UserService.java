package com.proj.bfftaskscheduler.business;

import com.proj.bfftaskscheduler.business.dto.in.AddressDTORequest;
import com.proj.bfftaskscheduler.business.dto.in.LoginRequest;
import com.proj.bfftaskscheduler.business.dto.in.TelephoneDTORequest;
import com.proj.bfftaskscheduler.business.dto.in.UserDTORequest;
import com.proj.bfftaskscheduler.business.dto.out.AddressDTOResponse;
import com.proj.bfftaskscheduler.business.dto.out.TelephoneDTOResponse;
import com.proj.bfftaskscheduler.business.dto.out.UserDTOResponse;
import com.proj.bfftaskscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient client;

    public UserDTOResponse saveUser(UserDTORequest userDTO) {

        return client.saveUser(userDTO);
    }

    public String loginUser(LoginRequest userDTO) {
        return client.login(userDTO);
    }


    public UserDTOResponse searchUserByEmail(String email, String token) {
        return client.searchUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token) {
        client.searchUserByEmail(email, token);
    }

    public UserDTOResponse updateUserData(String token, UserDTORequest userDTO) {
        return client.updateUserData(userDTO, token);
    }

    public AddressDTOResponse updateAddress(Long idAddress, AddressDTORequest addressDTO, String token) {

        return client.updateAddress(addressDTO, idAddress, token);

    }

    public TelephoneDTOResponse updateTelephone(Long idTelephone, TelephoneDTORequest telephoneDTO, String token) {
        return client.updateTelephone(telephoneDTO, idTelephone, token);
    }

    public AddressDTOResponse registerAddress(String token, AddressDTORequest addressDTO) {
        return client.registerAddres(addressDTO, token);
    }

    public TelephoneDTOResponse registerTelephone(String token, TelephoneDTORequest telephoneDTO) {
        return client.registerTelephone(telephoneDTO, token);
    }

}
