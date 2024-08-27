package ru.kolivim.estimates.calculator.domain.estimate;

import jakarta.persistence.*;
import lombok.*;
import ru.kolivim.estimates.calculator.api.dto.estimate.Status;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "elements")
public class Element extends BaseEntity {

    @Column(name="works_id")
    private UUID worksId;

    @Column(name="revision")
    private String revision;

    @Column(name="is_parent")
    private Boolean isParent;

    @Column(name="parent_id")
    private UUID parentId;

    @Column(name="\"group\"")
    private Integer group;

    @Column(name="work_price_list")
    private UUID workPriceListId;

    @Column(name="material_price_list")
    private UUID materialPriceListId;

    @Column(name="description")
    private String description;

    @Column(name="last_author_id")
    private UUID lastAuthorId;

    @Column(name="last_modified_date")
    private ZonedDateTime lastModifiedDate;

    @Column(name="version")
    private Integer version;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

}


/**
 id                  uuid not null
 primary key,
 is_deleted          boolean,
 works_id            uuid,
 revision            varchar(255),
 is_parent           boolean,
 parent_id           uuid,

 "group"             integer,
 work_price_list     uuid,
 material_price_list uuid,
 description         text,
 last_author_id      uuid,
 last_modified_date  timestamp(6) with time zone,
 version             integer,
 status              varchar(255)
 */