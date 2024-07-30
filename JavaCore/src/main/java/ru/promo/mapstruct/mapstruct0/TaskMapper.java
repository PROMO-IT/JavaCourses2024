package ru.promo.mapstruct.mapstruct0;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskAnswer toTaskAnswer(Task task);

}
