package ru.promo.mapstruct.mapstruct2;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EmployeeMapper.class)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mappings({
            @Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "updateDate", expression = "java(java.time.LocalDateTime.now())"),
    })
    TaskAnswer toTaskAnswer(Task task);

}
