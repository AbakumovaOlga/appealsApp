package ru.abakumova.appealsapp.mappers;

import org.mapstruct.Mapper;
import ru.abakumova.appealsapp.dto.AppealDto;
import ru.abakumova.appealsapp.models.Appeal;

@Mapper(componentModel = "spring")
public interface AppealMapper {
    AppealDto fromModel(Appeal model);
    Appeal fromAppealDto (AppealDto appealDto);
}
