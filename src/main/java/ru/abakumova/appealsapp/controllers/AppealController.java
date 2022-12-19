package ru.abakumova.appealsapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.AppealDto;
import ru.abakumova.appealsapp.dto.AppealStatusDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.exceptions.UndeletableAppealException;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Appeal;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.security.services.AppealService;
import ru.abakumova.appealsapp.security.services.EmployeeService;
import ru.abakumova.appealsapp.security.services.ManagerService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/appeal")
public class AppealController {

    private final AppealService appealService;

    @PostMapping("/employee")
    public void createAppeal(@Valid @RequestBody AppealDto appealDto, @AuthenticationPrincipal Account account) throws NoEntityException {
        appealService.create(appealDto, account);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteAppeal(@PathVariable Long id, @AuthenticationPrincipal Account account) throws UndeletableAppealException, NoEntityException {
        appealService.delete(account, id);
    }

    @GetMapping("/manager/newAppeals")
    public List<Appeal> getNewAppeals(@AuthenticationPrincipal Account account) throws NoEntityException {
        return appealService.getNewAppealsByManager(account);
    }

    @PutMapping("/manager/{id}")
    public void changeStatus(@PathVariable Long id, @RequestBody AppealStatusDto appealStatusDto, @AuthenticationPrincipal Account account) throws NoEntityException {
        appealService.changeStatus(account, id, AppealStatus.valueOf(appealStatusDto.getAppealStatus()));
    }
}
