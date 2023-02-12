package ru.abakumova.appealsapp.models;

import lombok.Data;
import ru.abakumova.appealsapp.dto.AppealDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class VacationRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @NotNull
    private int vacationCount;

    @NotNull
    private Date date;

    @OneToOne(mappedBy = "vacationRegister")
    private Appeal appeal;

    public VacationRegister( Appeal appeal, Integer vacationCount) {
        this.date=appeal.getDate();
        this.vacationCount=vacationCount;
        this.employee=appeal.getEmployee();
        this.appeal =appeal;
    }

    public VacationRegister() {

    }

    public VacationRegister(Employee employee, Integer vacationCount, Date date) {
        this.date=date;
        this.employee=employee;
        this.vacationCount=vacationCount;
    }
}
