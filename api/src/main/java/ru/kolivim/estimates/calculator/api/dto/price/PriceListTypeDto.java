package ru.kolivim.estimates.calculator.api.dto.price;

import lombok.Data;

@Data
public class PriceListTypeDto {

    String name;
    String description;
    Integer type;

}

/**

 id                 uuid not null
 primary key,
 is_deleted         boolean,
 type               integer,
 name               varchar(255),
 description        text,
 last_author_id     uuid,
 last_modified_date timestamp(6) with time zone,
 version            integer

 */