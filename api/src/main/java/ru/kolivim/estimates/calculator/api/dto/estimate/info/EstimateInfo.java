package ru.kolivim.estimates.calculator.api.dto.estimate.info;

import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.EstimateElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.Status;
import ru.kolivim.estimates.calculator.api.dto.estimate.info.EstimateElementInfoDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class EstimateInfo extends BaseDto { // TODO: Может сделать аналогично остальным INFO ???

    private String name;

    private UUID workPriceListId;

    private UUID materialPriceListId;

    private String description;

    private UUID lastAuthorId;

    private ZonedDateTime lastModifiedDate;

    private Integer version;  /** Здесь храним номер сметы */

    private Status status;

    private List<EstimateElementDto> estimateElementDtoList;    /** Убрать после замены на List<EstimateElementInfoDto> */

    private List<EstimateElementInfoDto> estimateElementInfoDtoList;

}
