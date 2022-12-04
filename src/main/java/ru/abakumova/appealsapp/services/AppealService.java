package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.exceptions.UndeletableAppealException;
import ru.abakumova.appealsapp.models.Appeal;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.repositories.AppealRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AppealService {

    private final AppealRepository appealRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AmqpTemplate rabbitTemplate;

    @Transactional
    public void create(Appeal appeal) {
        Appeal savedAppeal = appealRepository.save(appeal);
    }

    public List<Appeal> getNewAppealsByManager(Manager manager) {
        return appealRepository.getAppealsByManagerAndAppealStatus(manager, AppealStatus.NEW);
    }

    public void delete(Employee employee, Long id) throws UndeletableAppealException {
        Appeal appeal = appealRepository.findAppealById(id);
        if (appeal.getEmployee() == employee && appeal.getAppealStatus() == AppealStatus.NEW) {
            appealRepository.delete(appeal);
        } else {
            throw new UndeletableAppealException("You can`t delete this appeal because it`s not your appeal or this appeal has already been considered ");
        }
        //TODO: exc


    }

    public void changeStatus(Manager manager, Long id, AppealStatus appealStatus) {
        Appeal appeal = appealRepository.findAppealById(id);
        List<Appeal> appeals = getNewAppealsByManager(manager);
        if (appeals.contains(appeal)) {
            appeal.setAppealStatus(appealStatus);
            if(appealStatus.name().equals(AppealStatus.REJECTED.name())){
                rabbitTemplate.convertAndSend("queue_"+AppealStatus.REJECTED.name(),appeal);
            }else {
                kafkaTemplate.send(appealStatus.name(), appeal);
            }
            appealRepository.save(appeal);
        }
    }
}
