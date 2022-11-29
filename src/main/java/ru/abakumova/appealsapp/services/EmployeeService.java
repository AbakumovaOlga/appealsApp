package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.repositories.EmployeeRepository;
import ru.abakumova.appealsapp.repositories.ManagerRepository;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void create(Employee employee) {

        //  manager.getEmployees().forEach(employee -> employee.setManager(manager));
        Employee savedEmployee = employeeRepository.save(employee);

    }

    public Employee findByUsername(String username) {
        //TODO: add exc
        return employeeRepository.findByUsername(username);
    }
}
