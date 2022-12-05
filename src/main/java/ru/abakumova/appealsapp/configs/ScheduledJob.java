package ru.abakumova.appealsapp.configs;

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



    @Scheduled(cron="${taskEmail.cron}")
    public void taskEmail() throws InterruptedException {
        for (Manager manager : managerService.getManagers()) {
            int count = appealService.getNewAppealsByManager(manager).size();
            System.out.println(manager.getUsername() + ": " + count);
            if (count != 0) {
                emailService.sendTextEmail(manager.getEmail(), "notification", "you have " + count + " new appeals");
            }

        }
    }
}
