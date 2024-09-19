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
@Table(name = "estimate")
public class Estimate extends BaseEntity {

    @Column(name="name")
    private String name;

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
