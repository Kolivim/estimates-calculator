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
@Table(name = "estimate_element")
public class EstimateElement extends BaseEntity {

    @Column(name="estimate_id")
    private UUID estimateId;

    @Column(name="element_id")
    private UUID elementId;

    @Column(name="quantity")
    private Double quantity;

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


    /**
     id                  uuid,
     is_deleted          boolean,
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
