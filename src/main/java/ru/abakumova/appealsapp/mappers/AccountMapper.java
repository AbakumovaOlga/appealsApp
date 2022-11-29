package ru.abakumova.appealsapp.mappers;

import org.mapstruct.Mapper;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.models.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account fromRegisterDto(RegisterDto dto);
}
