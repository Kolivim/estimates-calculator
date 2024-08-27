package ru.kolivim.estimates.calculator.api.dto.estimate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ElementDto extends BaseDto {

    private UUID worksId;

    private String revision;

    private Boolean isParent;

    private UUID parentId;

    private Integer group;

    private UUID workPriceListId;

    private UUID materialPriceListId;

    private String description;

    private UUID lastAuthorId;

    private ZonedDateTime lastModifiedDate;

    private Integer version;

    private Status status;

}
