package com.proj.bfftaskscheduler.infrastructure.client;


import com.proj.bfftaskscheduler.business.dto.out.TasksDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

   void  sendEmail(@RequestBody TasksDTOResponse dto);
}