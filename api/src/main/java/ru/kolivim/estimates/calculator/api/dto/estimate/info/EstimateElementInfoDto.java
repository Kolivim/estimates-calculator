package ru.kolivim.estimates.calculator.api.dto.estimate.info;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.ElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.EstimateElementDto;

import java.util.List;

@Data
public class EstimateElementInfoDto extends BaseDto {

    private EstimateElementDto estimateElementDto;

    private ElementInfoDto elementInfoDto;

//    private List<ElementInfoDto> elementInfoDtoList;

}
