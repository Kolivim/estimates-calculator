package ru.kolivim.estimates.calculator.impl.mapper.base;

import org.mapstruct.Mapper;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;

@Mapper
public interface BaseMapper {
    BaseDto modelToDto(BaseEntity baseEntity);
    BaseEntity dtoToModel(BaseDto baseDto);
}
