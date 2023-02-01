package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.dto.AppealDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.exceptions.UndeletableAppealException;
import ru.abakumova.appealsapp.mappers.AppealListMapper;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Appeal;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.repositories.AppealRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AppealService {

    private final AppealRepository appealRepository;
    private final AppealListMapper appealListMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AmqpTemplate rabbitTemplate;

    private final EmployeeService employeeService;
    private final ManagerService managerService;

    @Transactional
    public void create(AppealDto appealDto, Account account) throws NoEntityException {
        Appeal appeal = new Appeal();
        appeal.setAppealType(appealDto.getAppealType());
        appeal.setAppealStatus(AppealStatus.NEW);
        appeal.setDate(new Date());
        appeal.setEmployee(employeeService.findByUsername(account.getUsername()));
        Appeal savedAppeal = appealRepository.save(appeal);
    }

    public List<AppealDto> getNewAppealsByManager(Account account) throws NoEntityException {
        Manager manager = managerService.findByUsername(account.getUsername());
        List<Appeal> appeals = appealRepository.getAppealsByManagerAndAppealStatus(manager, AppealStatus.NEW);
        return appealListMapper.fromModelList(appeals);
    }

    public List<Appeal> getNewAppealsByManager(Manager manager) {
        return appealRepository.getAppealsByManagerAndAppealStatus(manager, AppealStatus.NEW);
    }

    public void delete(Account account, Long id) throws UndeletableAppealException, NoEntityException {
        Employee employee = employeeService.findByUsername(account.getUsername());
        Appeal appeal = appealRepository.findAppealById(id).orElseThrow(() -> new NoEntityException(Appeal.class));
        if (appeal.getEmployee().equals(employee) && appeal.getAppealStatus().equals(AppealStatus.NEW)) {
            appealRepository.delete(appeal);
        } else {
            throw new UndeletableAppealException("You can`t delete this appeal because it`s not your appeal or this appeal has already been considered ");
        }
        //TODO: exc


    }

    public void changeStatus(Account account, Long id, AppealStatus appealStatus) throws NoEntityException {
        Manager manager = managerService.findByUsername(account.getUsername());

        Appeal appeal = appealRepository.findAppealById(id).orElseThrow(() -> new NoEntityException(Appeal.class));
        List<Appeal> appeals = getNewAppealsByManager(manager);
        if (appeals.contains(appeal)) {
            appeal.setAppealStatus(appealStatus);
            if (appealStatus.name().equals(AppealStatus.REJECTED.name())) {
                rabbitTemplate.convertAndSend("queue_" + AppealStatus.REJECTED.name(), appeal);
            } else {
                kafkaTemplate.send(appealStatus.name(), appeal);
            }
            appealRepository.save(appeal);
        }
    }
}
