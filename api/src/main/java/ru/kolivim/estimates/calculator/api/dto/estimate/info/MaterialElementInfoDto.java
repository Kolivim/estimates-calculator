package ru.kolivim.estimates.calculator.api.dto.estimate.info;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.MaterialElementDto;

@Data
public class MaterialElementInfoDto extends BaseDto {

    private MaterialElementDto materialElementDto;

    private String materialName;

    private Double materialPrice;

    private Double summaryQuantity; /** Не выводится, итоговое количество на единицу работы, после перемножения всех единичных коэффициентов consumptionRate, reductionFactor, numberLayers, layerThickness и quantity */

    private Double totalQuantityForWork; /** Выводится, итоговое количество материала, на весь объем работ */

    private Double totalPriceForWork; /** Выводится, итоговая стоимость материала, на весь объем работ */

}
