package com.proj.bfftaskscheduler.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelephoneDTOResponse {

    private Long id;
    private String number;
    private String ddd;

}
