package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.abakumova.appealsapp.clients.ProjectClient;
import ru.abakumova.appealsapp.dto.VacationForProjectDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.VacationRegister;
import ru.abakumova.appealsapp.repositories.VacationRegisterRepository;

import java.util.Date;

@Service
@AllArgsConstructor
public class VacationRegisterService {
    private final VacationRegisterRepository vacationRegisterRepository;
    private final EmployeeService employeeService ;
    private final ManagerService managerService;
    private final ProjectClient projectClient;

    public void provideVacationForProject(VacationForProjectDto vacationForProjectDto, Account account) throws NoEntityException {
        Employee employee=employeeService.findByUsername(vacationForProjectDto.getUsername());
        Manager manager=managerService.findByUsername(account.getUsername());

        if(manager.getEmployees().contains(employee)){
            //TODO: get randomInteger from Random.org;
            //Integer vacationCount=5;
            Integer vacationCount=projectClient.getVacationForProject(vacationForProjectDto.getProjectId(),1,1,1,10,"plain","new");
            vacationRegisterRepository.save(new VacationRegister(employee, vacationCount, new Date()));
        }else{
            throw new NoEntityException(Employee.class);
        }
    }
}
