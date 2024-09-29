package ru.kolivim.estimates.calculator.impl.repository.element;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kolivim.estimates.calculator.api.dto.estimate.Status;
import ru.kolivim.estimates.calculator.domain.estimate.Element;
import ru.kolivim.estimates.calculator.domain.estimate.EstimateElement;
import ru.kolivim.estimates.calculator.domain.estimate.MaterialElement;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface MaterialElementRepository extends BaseRepository<MaterialElement> {

//    List<MaterialElement> findByElementIdAndIsDeleted (UUID elementId, Boolean isDeleted);

    @Query(value = "SELECT * FROM material_elements where id_element = :find_id_element and is_deleted = :find_is_deleted", nativeQuery = true)
    List<MaterialElement> findAllMaterialsWork(@Param("find_id_element") UUID elementId, @Param("find_is_deleted") Boolean isDeleted);

}

/**
 create table material_elements
 (
 id                 uuid not null
 primary key,
 is_deleted         boolean,
 id_element         varchar(255),
 material_id        uuid,
 quantity           numeric(16, 2),
 consumption_rate   numeric(16, 2),
 reduction_factor   numeric(16, 2),
 number_layers      numeric(16, 2),
 layer_thickness    numeric(16, 2),
 description        text,
 last_author_id     uuid,
 last_modified_date timestamp(6) with time zone
 );

 alter table material_elements
 owner to estimates;
 */