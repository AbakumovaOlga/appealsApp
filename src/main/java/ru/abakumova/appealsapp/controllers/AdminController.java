package ru.abakumova.appealsapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.EmployeeDto;
import ru.abakumova.appealsapp.dto.ManagerDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.mappers.AccountMapper;
import ru.abakumova.appealsapp.mappers.EmployeeMapper;
import ru.abakumova.appealsapp.mappers.ManagerMapper;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AccountRole;
import ru.abakumova.appealsapp.security.services.AccountService;
import ru.abakumova.appealsapp.security.services.EmployeeService;
import ru.abakumova.appealsapp.security.services.ManagerService;

import javax.validation.Valid;


@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ManagerService managerService;
    private final EmployeeService employeeService;

    @PostMapping("/manager")
    public void createManager(@Valid @RequestBody ManagerDto managerDto) {
        managerService.create(managerDto);
    }

    @PostMapping("/employee")
    public void createEmployee(@RequestBody @Valid EmployeeDto employeeDto) throws NoEntityException {
        employeeService.create(employeeDto);
    }
}
