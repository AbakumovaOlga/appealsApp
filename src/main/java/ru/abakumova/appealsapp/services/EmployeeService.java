package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.repositories.EmployeeRepository;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void create(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
    }

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
}
