package ru.kolivim.myproject.task.management.system.impl.mapper.base;

import org.mapstruct.Mapper;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;

@Mapper
public interface BaseMapper {
    BaseDto modelToDto(BaseEntity baseEntity);
    BaseEntity dtoToModel(BaseDto baseDto);
}
