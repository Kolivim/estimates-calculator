package ru.kolivim.estimates.calculator.api.dto.estimate;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class MaterialElementDto extends BaseDto {

    private UUID elementId; //  id_element

    private UUID materialId;    // material_id

    private Double quantity; // quantity

    private Double consumptionRate; //  consumption_rate

    private Double reductionFactor; // reduction_factor

    private Double numberLayers; // number_layers

    private Double layerThickness; // layer_thickness

    private String description; //  description

    private UUID lastAuthorId;  // last_author_id

    private ZonedDateTime lastModifiedDate; //  last_modified_date

    /*
    id_element         varchar(255),
    material_id        uuid,
    quantity           numeric(16, 2),
    consumption_rate   numeric(16, 2),
    reduction_factor   numeric(16, 2),
    number_layers      numeric(16, 2),
    layer_thickness    numeric(16, 2),
    description        text,
    last_author_id     uuid,
    last_modified_date timestamp(6) with time zone
    */

}
