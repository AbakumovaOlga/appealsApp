package ru.abakumova.appealsapp.models;

import lombok.Data;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.models.enums.AppealType;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(value = EnumType.STRING)
    private AppealType appealType;

    @Enumerated(value = EnumType.STRING)
    private AppealStatus appealStatus;

    private Date date;

}
