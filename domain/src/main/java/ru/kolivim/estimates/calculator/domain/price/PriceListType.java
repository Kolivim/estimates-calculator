package ru.kolivim.estimates.calculator.domain.price;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type_price_list")
public class PriceListType extends BaseEntity {

    @Column(name="id")
    private UUID id;

    @Column(name="is_deleted")
    private Boolean isDeleted;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private Integer type;

    @Column(name="description")
    private String description;

    @Column(name="last_author_id")
    private UUID lastAuthorId;

    @Column(name="last_modified_date")
    private ZonedDateTime lastModifiedDate;

    @Column(name="version")
    private Integer version;

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