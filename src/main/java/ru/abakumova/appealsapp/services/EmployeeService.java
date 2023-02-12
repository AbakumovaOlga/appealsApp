package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.dto.EmployeeDto;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.mappers.AccountMapper;
import ru.abakumova.appealsapp.mappers.EmployeeMapper;
import ru.abakumova.appealsapp.mappers.ManagerMapper;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.enums.AccountRole;
import ru.abakumova.appealsapp.repositories.EmployeeRepository;
import ru.abakumova.appealsapp.repositories.VacationRegisterRepository;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ManagerService managerService;
    private final AccountService accountService;
    private final EmployeeMapper employeeMapper;
    private final VacationRegisterRepository vacationRegisterRepository;

    @Transactional
    public void create(EmployeeDto employeeDto) throws NoEntityException {
        RegisterDto registerDto =new RegisterDto() ;
        registerDto.setUsername(employeeDto.getUsername());
        registerDto.setPassword(employeeDto.getPassword());
        registerDto.setRole(AccountRole.EMPLOYEE);
        accountService.register(registerDto);
        Employee employee = employeeMapper.fromEmployeeDto(employeeDto);
        employee.setManager(managerService.findByUsername(employeeDto.getManager_username()));
        Employee savedEmployee = employeeRepository.save(employee);
    }

    public Employee findByUsername(String username) throws NoEntityException {
        return employeeRepository.findByUsername(username).orElseThrow(() -> new NoEntityException(Employee.class));
    }

    public Integer getAvailableVacationCount(Account account) throws NoEntityException {
        Employee employee = findByUsername(account.getUsername());
        return vacationRegisterRepository.getSumByEmployee(employee).orElse(0);
    }
}
