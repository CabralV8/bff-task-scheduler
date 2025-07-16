package com.proj.bfftaskscheduler.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proj.bfftaskscheduler.business.enums.StatusNotificationEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TasksDTORequest {

    private String taskName;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;


}
