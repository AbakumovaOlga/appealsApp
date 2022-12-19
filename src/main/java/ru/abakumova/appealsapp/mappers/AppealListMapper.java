package ru.abakumova.appealsapp.mappers;

import org.mapstruct.Mapper;
import ru.abakumova.appealsapp.dto.AppealDto;
import ru.abakumova.appealsapp.models.Appeal;

import java.util.List;

@Mapper(componentModel = "spring", uses = AppealMapper.class)
public interface AppealListMapper {
    List<AppealDto> fromModelList(List<Appeal> models);
}
