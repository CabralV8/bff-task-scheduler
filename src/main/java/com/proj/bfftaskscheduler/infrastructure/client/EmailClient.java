package com.proj.bfftaskscheduler.infrastructure.client;


import com.proj.bfftaskscheduler.business.dto.out.TasksDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

   @PostMapping
   void sendEmail(@RequestBody TasksDTOResponse dto);
}