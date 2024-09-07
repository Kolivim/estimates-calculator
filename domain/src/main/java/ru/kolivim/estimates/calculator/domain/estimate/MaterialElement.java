package ru.kolivim.estimates.calculator.domain.estimate;

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
@Table(name = "material_elements")
public class MaterialElement extends BaseEntity {

    @Column(name="id_element")
    private UUID elementId;

    @Column(name="material_id")
    private UUID materialId;

    @Column(name="quantity")
    private Double quantity;

    @Column(name="consumption_rate")
    private Double consumptionRate;

    @Column(name="reduction_factor")
    private Double reductionFactor;

    @Column(name="number_layers")
    private Double numberLayers;

    @Column(name="layer_thickness")
    private Double layerThickness;

    @Column(name="description")
    private String description;

    @Column(name="last_author_id")
    private UUID lastAuthorId;

    @Column(name="last_modified_date")
    private ZonedDateTime lastModifiedDate;

}
