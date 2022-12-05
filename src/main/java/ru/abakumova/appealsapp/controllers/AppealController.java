package ru.abakumova.appealsapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.AppealDto;
import ru.abakumova.appealsapp.dto.AppealStatusDto;
import ru.abakumova.appealsapp.exceptions.UndeletableAppealException;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Appeal;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.services.AppealService;
import ru.abakumova.appealsapp.services.EmployeeService;
import ru.abakumova.appealsapp.services.ManagerService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/appeal")
public class AppealController {

    private final AppealService appealService;
    private final EmployeeService employeeService;
    private final ManagerService managerService;



    @PostMapping("/employee")
    public void createAppeal(@Valid @RequestBody AppealDto appealDto /*, @AuthenticationPrincipal Account account*/) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Appeal appeal = new Appeal();
        appeal.setAppealType(appealDto.getAppealType());
        appeal.setAppealStatus(AppealStatus.NEW);
        appeal.setDate(new Date());
        appeal.setEmployee(employeeService.findByUsername(account.getUsername()));

        appealService.create(appeal);

    }

    @DeleteMapping("/employee/{id}")
    public void deleteAppeal(@PathVariable Long id) throws UndeletableAppealException {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByUsername(account.getUsername());
        appealService.delete(employee, id);
    }

    @GetMapping("/manager/newAppeals")
    public List<Appeal> getNewAppeals() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Manager manager = managerService.findByUsername(account.getUsername());
        return appealService.getNewAppealsByManager(manager);
    }

    @PutMapping("/manager/{id}")
    public void changeStatus(@PathVariable Long id, @RequestBody AppealStatusDto appealStatusDto) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Manager manager = managerService.findByUsername(account.getUsername());
        appealService.changeStatus(manager, id, AppealStatus.valueOf(appealStatusDto.getAppealStatus()));
    }
}
