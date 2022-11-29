package ru.abakumova.appealsapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.EmployeeDto;
import ru.abakumova.appealsapp.dto.LoginDto;
import ru.abakumova.appealsapp.dto.ManagerDto;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.mappers.AccountMapper;
import ru.abakumova.appealsapp.mappers.ManagerMapper;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AccountRole;
import ru.abakumova.appealsapp.services.AccountService;
import ru.abakumova.appealsapp.services.EmployeeService;
import ru.abakumova.appealsapp.services.ManagerService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerMapper managerMapper;

    @PostMapping("/manager")
    public void createManager(@Valid @RequestBody ManagerDto managerDto) {
        /*Account account = accountMapper.fromRegisterDto(registerDto);
        accountService.register(account);
        Manager manager = managerMapper.fromManagerDto(managerDto);
        managerService.create(manager);*/
        //TODO: constr or mapper
        Account account=new Account();
        account.setPassword(managerDto.getPassword());
        account.setUsername(managerDto.getUsername());
        account.setRole(AccountRole.MANAGER);
        accountService.register(account);

        Manager manager = new Manager();
        manager.setFio(managerDto.getFio());
        manager.setEmail(managerDto.getEmail());
        manager.setUsername(managerDto.getUsername());
        managerService.create(manager);

    }

    @PostMapping("/employee")
    public void createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        /*Account account = accountMapper.fromRegisterDto(registerDto);
        accountService.register(account);
        Manager manager = managerMapper.fromManagerDto(managerDto);
        managerService.create(manager);*/
        //TODO: constr or mapper
        Account account=new Account();
        account.setPassword(employeeDto.getPassword());
        account.setUsername(employeeDto.getUsername());
        account.setRole(AccountRole.EMPLOYEE);
        accountService.register(account);

        Employee employee = new Employee();
        employee.setFio(employeeDto.getFio());
        employee.setEmail(employeeDto.getEmail());
        employee.setUsername(employeeDto.getUsername());
        employee.setManager(managerService.findByUsername(employeeDto.getManager_username()));
        employeeService.create(employee);

    }
}
