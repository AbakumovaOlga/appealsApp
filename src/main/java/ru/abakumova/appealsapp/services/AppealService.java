package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.dto.AppealDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.exceptions.NotEnoughVacationCount;
import ru.abakumova.appealsapp.exceptions.UndeletableAppealException;
import ru.abakumova.appealsapp.mappers.AppealListMapper;
import ru.abakumova.appealsapp.mappers.AppealMapper;
import ru.abakumova.appealsapp.models.*;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.repositories.AppealRepository;
import ru.abakumova.appealsapp.repositories.VacationRegisterRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AppealService {

    private final AppealRepository appealRepository;
    private final AppealListMapper appealListMapper;
    private final AppealMapper appealMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AmqpTemplate rabbitTemplate;

    private final EmployeeService employeeService;
    private final ManagerService managerService;

    private final VacationRegisterRepository vacationRegisterRepository;

    @Transactional
    public void create(AppealDto appealDto, Account account) throws NoEntityException, NotEnoughVacationCount {
        appealDto.setAppealStatus(AppealStatus.NEW);
        appealDto.setDate(new Date());
        appealDto.setEmployee(employeeService.findByUsername(account.getUsername()));
        Appeal appeal = appealMapper.fromAppealDto(appealDto);
        if (employeeService.getAvailableVacationCount(account) < appealDto.getVacationCount()){
            throw new NotEnoughVacationCount("You don`t have enough vacations day");
        }else{
            VacationRegister savedVacationRegister = vacationRegisterRepository.save(new VacationRegister(appeal, 0-appealDto.getVacationCount()));
            appeal.setVacationRegister(savedVacationRegister);
            Appeal savedAppeal = appealRepository.save(appeal);
        }
}

    public List<AppealDto> getAppealsByManagerAndStatus(Account account, AppealStatus appealStatus) throws NoEntityException {
        Manager manager = managerService.findByUsername(account.getUsername());
        List<Appeal> appeals = appealRepository.getAppealsByManagerAndAppealStatus(manager, appealStatus);
        return appealListMapper.fromModelList(appeals);
    }

    public List<Appeal> getAppealsByManagerAndStatus(Manager manager, AppealStatus appealStatus) {
        return appealRepository.getAppealsByManagerAndAppealStatus(manager, appealStatus);
    }

    public void delete(Account account, Long id) throws UndeletableAppealException, NoEntityException {
        Employee employee = employeeService.findByUsername(account.getUsername());
        Appeal appeal = appealRepository.findAppealById(id).orElseThrow(() -> new NoEntityException(Appeal.class));
        if (appeal.getEmployee().equals(employee) && appeal.getAppealStatus().equals(AppealStatus.NEW)) {
            appealRepository.delete(appeal);
            vacationRegisterRepository.delete(appeal.getVacationRegister());
        } else {
            throw new UndeletableAppealException("You can`t delete this appeal because it`s not your appeal or this appeal has already been considered ");
        }


    }

    public void changeStatus(Account account, Long id, AppealStatus appealStatus) throws NoEntityException {
        Manager manager = managerService.findByUsername(account.getUsername());

        Appeal appeal = appealRepository.findAppealById(id).orElseThrow(() -> new NoEntityException(Appeal.class));
        List<Appeal> appeals = getAppealsByManagerAndStatus(manager, AppealStatus.NEW);
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
