package com.proj.bfftaskscheduler.business;

import com.proj.bfftaskscheduler.business.dto.in.LoginRequest;
import com.proj.bfftaskscheduler.business.dto.out.TasksDTOResponse;
import com.proj.bfftaskscheduler.business.enums.StatusNotificationEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TasksService tasksService;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;

    @Value("${user.password}")
    private String password;

    @Scheduled(cron = "${cron.time}")
    public void searchNextHourTasks() {
        String token = login(converterToRequestDTO());
        log.info("Tasks search started...");
        LocalDateTime futureHour = LocalDateTime.now().plusHours(1);
        LocalDateTime futureHourPlusFive = LocalDateTime.now().plusHours(1).plusMinutes(5);


        List<TasksDTOResponse> taskList = tasksService.searchTasksScheduledByPeriod(futureHour, futureHourPlusFive, token);
        log.info("Tasks found: " + taskList);

        taskList.forEach(task ->
        {emailService.sendEmail(task);
            log.info("Email sent to user: " + task.getUserEmail());
            tasksService.changeStatus(StatusNotificationEnum.NOTIFIED, task.getId(), token);});

    log.info("Search and notification finished.");
    }

    public String login(LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    public LoginRequest converterToRequestDTO() {
        return LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

}
