package ru.abakumova.appealsapp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.services.AppealService;
import ru.abakumova.appealsapp.services.ManagerService;

@Component
public class ScheduledJob {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private AppealService appealService;

    @Autowired
    EmailService emailService;

    //@Scheduled(cron = "*/3 * * ? * *")
   // @Scheduled(cron="${taskEmail.cron}")
    public void taskEmail() throws InterruptedException {
        for (Manager manager : managerService.getManagers()
        ) {
            int count = appealService.getNewAppealsByManager(manager).size();
            System.out.println(manager.getUsername()+": "+count);
            if(count!=0){
                emailService.sendTextEmail(manager.getEmail(), "notification", "you have "+count+" new appeals");
            }

        }
    }
}
