package ru.abakumova.appealsapp.schedules;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.services.AppealService;
import ru.abakumova.appealsapp.services.EmailService;
import ru.abakumova.appealsapp.services.ManagerService;

@Component
@AllArgsConstructor
public class ScheduledJob {
    private final ManagerService managerService;

    private final AppealService appealService;

    private final EmailService emailService;


    @Scheduled(cron = "${taskEmail.cron}")
    public void taskEmail() {
        String textEmail = "new appeals: ";
        managerService.getManagers().stream()
                .filter(manager -> appealService.getNewAppealsByManager(manager).size() != 0)
                .forEach(manager -> emailService.sendTextEmail(manager.getEmail(), "notification", textEmail + appealService.getNewAppealsByManager(manager).size()));
    }
}
