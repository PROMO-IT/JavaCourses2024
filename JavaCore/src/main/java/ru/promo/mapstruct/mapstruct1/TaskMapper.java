package ru.promo.mapstruct.mapstruct1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mappings({
            @Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "updateDate", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "employeeName", source = "employee", qualifiedByName = "employeeToString"),
            @Mapping(target = "number", ignore = true)
    })
    TaskAnswer toTaskAnswer(Task task);

    @Named("employeeToString")
    default String employeeToString(Employee employee) {
        return employee.name;
    }
}
