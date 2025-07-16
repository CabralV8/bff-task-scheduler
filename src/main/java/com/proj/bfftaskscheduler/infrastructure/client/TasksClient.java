package com.proj.bfftaskscheduler.infrastructure.client;



import com.proj.bfftaskscheduler.business.dto.in.TasksDTORequest;
import com.proj.bfftaskscheduler.business.dto.out.TasksDTOResponse;
import com.proj.bfftaskscheduler.business.enums.StatusNotificationEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks-scheduler", url = "${tasks-scheduler.url}")
public interface TasksClient {

    @PostMapping
    TasksDTOResponse recordTasks(@RequestBody TasksDTORequest tasksDTO,
                                 @RequestHeader("Authorization") String token);


    @GetMapping("/events")
    List<TasksDTOResponse> searchSchedulesListsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader("Authorization") String token);


    @GetMapping
    List<TasksDTOResponse> searchTasksByEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TasksDTOResponse changeStatusNotification(@RequestParam("status") StatusNotificationEnum status,
                                              @RequestParam("id") String id,
                                              @RequestHeader("Authorization") String token);

    @PutMapping
    TasksDTOResponse updateTasks(@RequestBody TasksDTOResponse dto,
                                 @RequestParam("id") String id,
                                 @RequestHeader("Authorization") String token);

}