package ru.abakumova.appealsapp.mappers;

import org.mapstruct.Mapper;
import ru.abakumova.appealsapp.dto.ManagerDto;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Manager;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    Manager fromManagerDto(ManagerDto dto);

}
