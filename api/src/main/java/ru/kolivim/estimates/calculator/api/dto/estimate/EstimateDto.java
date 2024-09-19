package ru.kolivim.estimates.calculator.api.dto.estimate;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class EstimateDto extends BaseDto {

    private String name;

    private UUID workPriceListId;

    private UUID materialPriceListId;

    private String description;

    private UUID lastAuthorId;

    private ZonedDateTime lastModifiedDate;

    private Integer version;  /** Здесь храним номер сметы */

    private Status status;

}

/**
 id                  uuid not null
 is_deleted          boolean,
 name                text,
 work_price_list     uuid,
 material_price_list uuid,
 description         text,
 last_author_id      uuid,
 last_modified_date  timestamp(6) with time zone,
 version             integer,
 status              varchar(255)
 */