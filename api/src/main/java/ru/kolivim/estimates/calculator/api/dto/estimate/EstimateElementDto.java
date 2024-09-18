package ru.kolivim.estimates.calculator.api.dto.estimate;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class EstimateElementDto extends BaseDto {

    private UUID estimateId;

    private UUID elementId;

    private Double quantity;

    private UUID workPriceListId;

    private UUID materialPriceListId;

    private String description;

    private UUID lastAuthorId;

    private ZonedDateTime lastModifiedDate;

    private Integer version;

    private Status status;


    /**
    id                  uuid,
    is_deleted          boolean,
    estimate_id         uuid,
    element_id          uuid,
    quantity            numeric(16, 2),
    work_price_list     uuid,
    material_price_list uuid,
    description         text,
    last_author_id      uuid,
    last_modified_date  timestamp(6) with time zone,
    version             integer,
    status              varchar(255)
    */

}

