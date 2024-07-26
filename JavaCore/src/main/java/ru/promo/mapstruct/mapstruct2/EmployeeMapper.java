package ru.promo.mapstruct.mapstruct2;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mappings({
            @Mapping(target = "status", constant = "CREATED")
    })
    EmployeeAnswer toEmployeeAnswer(Employee employee);
}
