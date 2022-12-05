package ru.abakumova.appealsapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.EmployeeDto;
import ru.abakumova.appealsapp.dto.ManagerDto;
import ru.abakumova.appealsapp.mappers.AccountMapper;
import ru.abakumova.appealsapp.mappers.EmployeeMapper;
import ru.abakumova.appealsapp.mappers.ManagerMapper;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AccountRole;
import ru.abakumova.appealsapp.services.AccountService;
import ru.abakumova.appealsapp.services.EmployeeService;
import ru.abakumova.appealsapp.services.ManagerService;

import javax.validation.Valid;


@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ManagerService managerService;
    private final AccountService accountService;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final ManagerMapper managerMapper;
    private final AccountMapper accountMapper;


    @PostMapping("/manager")
    public void createManager(@Valid @RequestBody ManagerDto managerDto) {
        Account account = accountMapper.fromManagerDto(managerDto);
        account.setRole(AccountRole.MANAGER);
        accountService.register(account);
        Manager manager = managerMapper.fromManagerDto(managerDto);
        managerService.create(manager);
    }

    @PostMapping("/employee")
    public void createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        Account account = accountMapper.fromEmployeeDto(employeeDto);
        account.setRole(AccountRole.EMPLOYEE);
        accountService.register(account);
        Employee employee = employeeMapper.fromEmployeeDto(employeeDto);
        employee.setManager(managerService.findByUsername(employeeDto.getManager_username()));
        employeeService.create(employee);
    }
}
