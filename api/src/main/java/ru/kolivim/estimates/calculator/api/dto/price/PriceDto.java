package ru.kolivim.estimates.calculator.api.dto.price;

import lombok.Data;

import java.util.UUID;

@Data
public class PriceDto {

    UUID priceListId;
    String name;
    Double price;
    String unitMeasurement;
    String description;

}
