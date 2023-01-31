package ru.abakumova.appealsapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.EmployeeDto;
import ru.abakumova.appealsapp.dto.ManagerDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.services.EmployeeService;
import ru.abakumova.appealsapp.services.ManagerService;

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
