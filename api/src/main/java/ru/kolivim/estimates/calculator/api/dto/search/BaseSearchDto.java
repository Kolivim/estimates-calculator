package ru.kolivim.estimates.calculator.api.dto.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BaseSearchDto {
    private UUID id;
    private List<UUID> ids;
    private Boolean isDeleted;
}
