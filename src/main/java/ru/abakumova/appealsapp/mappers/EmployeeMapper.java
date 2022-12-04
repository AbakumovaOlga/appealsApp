package ru.abakumova.appealsapp.mappers;

import org.mapstruct.Mapper;
import ru.abakumova.appealsapp.dto.EmployeeDto;
import ru.abakumova.appealsapp.models.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee fromEmployeeDto(EmployeeDto dto);
}
