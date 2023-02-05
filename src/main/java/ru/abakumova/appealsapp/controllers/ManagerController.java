package ru.abakumova.appealsapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.abakumova.appealsapp.dto.VacationForProjectDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.services.VacationRegisterService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final VacationRegisterService vacationRegisterService;
    @PostMapping("/provideVacationForProject")
    public void provideVacationForProject (@Valid @RequestBody VacationForProjectDto vacationForProjectDto, @AuthenticationPrincipal Account account) throws NoEntityException {
        vacationRegisterService.provideVacationForProject(vacationForProjectDto,account);
    }
}
