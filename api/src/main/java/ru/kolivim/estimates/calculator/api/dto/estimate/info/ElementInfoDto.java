package ru.kolivim.estimates.calculator.api.dto.estimate.info;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.ElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.EstimateElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.MaterialElementDto;

import java.util.List;

@Data
public class ElementInfoDto extends BaseDto {

    private ElementDto elementDto;

    private List<MaterialElementInfoDto> materialElementInfoDtoList;

    private String workName;

    private Double workPrice;

    private Double quantity; /** Выводится, объем работ */

    private Double totalPrice; /** Выводится, итоговая стоимость работ, на весь объем */

}
