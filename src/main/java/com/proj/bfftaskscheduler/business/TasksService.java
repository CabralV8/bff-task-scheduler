package com.proj.bfftaskscheduler.business;

import com.proj.bfftaskscheduler.business.dto.in.TasksDTORequest;
import com.proj.bfftaskscheduler.business.dto.out.TasksDTOResponse;
import com.proj.bfftaskscheduler.business.enums.StatusNotificationEnum;
import com.proj.bfftaskscheduler.infrastructure.client.TasksClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksClient tasksClient;

    public TasksDTOResponse recordTasks(String token, TasksDTORequest tasksDTO){
        return tasksClient.recordTasks(tasksDTO, token);
    }


    public List<TasksDTOResponse> searchTasksScheduledByPeriod(LocalDateTime initialDate,
                                                               LocalDateTime finalDate, String token) {
        return tasksClient.searchSchedulesListsByPeriod(initialDate, finalDate, token);
    }

    public List<TasksDTOResponse> searchTasksByEmail(String token) {

        return tasksClient.searchTasksByEmail(token);
    }

    public void deleteTaskById(String id, String token) {
       tasksClient.deleteTaskById(id, token);
    }

    public TasksDTOResponse changeStatus(StatusNotificationEnum status, String id, String token) {
       return tasksClient.changeStatusNotification(status, id, token);
    }

    public TasksDTOResponse updateTasks(TasksDTOResponse dto, String id, String token){
        return tasksClient.updateTasks(dto, id, token);
    }
}