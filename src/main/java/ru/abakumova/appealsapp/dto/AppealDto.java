package ru.abakumova.appealsapp.dto;

import lombok.Data;
import ru.abakumova.appealsapp.models.Employee;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.models.enums.AppealType;
@Data
public class AppealDto {

   // private String employee_username;
    private AppealType appealType;
}
