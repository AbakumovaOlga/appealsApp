package ru.abakumova.appealsapp.dto;

import lombok.Data;
import ru.abakumova.appealsapp.models.enums.AppealType;

@Data
public class AppealDto {
    private AppealType appealType;
}
