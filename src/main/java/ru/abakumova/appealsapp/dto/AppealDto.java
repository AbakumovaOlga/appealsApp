package ru.abakumova.appealsapp.dto;

import lombok.Data;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.models.enums.AppealType;

import java.util.Date;

@Data
public class AppealDto {
    private AppealType appealType;
    private Employee employee;
    private AppealStatus appealStatus;
    private Date date;
}
