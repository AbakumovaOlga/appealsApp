package ru.abakumova.appealsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
}
