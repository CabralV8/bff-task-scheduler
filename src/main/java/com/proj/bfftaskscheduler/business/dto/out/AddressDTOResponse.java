package com.proj.bfftaskscheduler.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTOResponse {

    private Long id;
    private String street;
    private Long number;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;

}
