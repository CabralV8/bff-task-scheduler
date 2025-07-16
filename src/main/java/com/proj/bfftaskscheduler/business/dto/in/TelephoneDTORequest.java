package com.proj.bfftaskscheduler.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelephoneDTORequest {

    private String number;
    private String ddd;

}
