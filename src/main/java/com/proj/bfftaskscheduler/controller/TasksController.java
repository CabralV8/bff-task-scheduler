package com.proj.bfftaskscheduler.controller;


import com.proj.bfftaskscheduler.business.TasksService;
import com.proj.bfftaskscheduler.business.dto.in.TasksDTORequest;
import com.proj.bfftaskscheduler.business.dto.out.TasksDTOResponse;
import com.proj.bfftaskscheduler.business.enums.StatusNotificationEnum;
import com.proj.bfftaskscheduler.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Manages user tasks")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    @Operation(summary = "Create a user task", description = "Creates and registers a new task for the user.")
    @ApiResponse(responseCode = "200", description = "Task successfully registered.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<TasksDTOResponse> recordTasks(@RequestBody TasksDTORequest tasksDTO,
                                                        @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.recordTasks(token, tasksDTO));
    }

    @GetMapping("/events")
    @Operation(summary = "Get tasks by date range", description = "Retrieves tasks scheduled within the specified date range.")
    @ApiResponse(responseCode = "200", description = "Tasks successfully found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<TasksDTOResponse>> searchSchedulesListsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.searchTasksScheduledByPeriod(initialDate, finalDate, token));
    }

    @GetMapping
    @Operation(summary = "Get tasks by user", description = "Retrieves all tasks associated with the authenticated user.")
    @ApiResponse(responseCode = "200", description = "Tasks successfully found.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<TasksDTOResponse>> searchTasksByEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        List<TasksDTOResponse> tasks = tasksService.searchTasksByEmail(token);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping
    @Operation(summary = "Delete task by ID", description = "Deletes a task using its ID.")
    @ApiResponse(responseCode = "200", description = "Task successfully deleted.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id,
                                               @RequestHeader(name = "Authorization", required = false) String token) {
        tasksService.deleteTaskById(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Update task status", description = "Changes the status of a task.")
    @ApiResponse(responseCode = "200", description = "Task status successfully changed.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<TasksDTOResponse> changeStatusNotification(@RequestParam("status") StatusNotificationEnum status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.changeStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Update task details", description = "Updates the data of a specific task.")
    @ApiResponse(responseCode = "200", description = "Task successfully updated.")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<TasksDTOResponse> updateTasks(@RequestBody TasksDTOResponse dto, @RequestParam("id") String id,
                                                        @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tasksService.updateTasks(dto, id, token));
    }
}

