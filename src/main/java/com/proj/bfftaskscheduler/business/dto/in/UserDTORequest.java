package com.proj.bfftaskscheduler.business.dto.in;

import com.proj.bfftaskscheduler.business.dto.out.AddressDTOResponse;
import com.proj.bfftaskscheduler.business.dto.out.TelephoneDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTORequest {

    private String name;
    private String email;
    private String password;

    private List<AddressDTOResponse> addresses;
    private List<TelephoneDTOResponse> telephones;
}
