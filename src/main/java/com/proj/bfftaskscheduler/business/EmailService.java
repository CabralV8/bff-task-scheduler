package com.proj.bfftaskscheduler.business;

import com.proj.bfftaskscheduler.business.dto.out.TasksDTOResponse;
import com.proj.bfftaskscheduler.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

   public void sendEmail(TasksDTOResponse dtoResponse){
        emailClient.sendEmail(dtoResponse);
   }

}
