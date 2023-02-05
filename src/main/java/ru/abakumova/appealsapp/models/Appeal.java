package ru.abakumova.appealsapp.models;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.models.enums.AppealType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private AppealType appealType;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private AppealStatus appealStatus;

    @NotNull
    private Date date;

    @OneToOne
    @JoinColumn(name = "vacationRegister_id", referencedColumnName = "id")
    private VacationRegister vacationRegister;
}
