package com.proj.bfftaskscheduler.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTORequest {

    private String street;
    private Long number;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;

}
