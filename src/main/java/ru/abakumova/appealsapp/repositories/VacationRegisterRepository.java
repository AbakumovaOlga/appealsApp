package ru.abakumova.appealsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.VacationRegister;

import java.util.Optional;


@Repository
public interface VacationRegisterRepository extends JpaRepository<VacationRegister, Long> {
    @Query(value = "select sum(vr.vacationCount) from VacationRegister vr where vr.employee = ?1 group by vr.employee ")
    Optional<Integer> getSumByEmployee(Employee employee);
}
