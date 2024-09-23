package ru.kolivim.estimates.calculator.api.dto.estimate;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class EstimateInfo extends BaseDto {

    private String name;

    private UUID workPriceListId;

    private UUID materialPriceListId;

    private String description;

    private UUID lastAuthorId;

    private ZonedDateTime lastModifiedDate;

    private Integer version;  /** Здесь храним номер сметы */

    private Status status;

    private List<EstimateElementDto> estimateElementDtoList;

}
