package ru.kolivim.estimates.calculator.impl.repository.element;

import jakarta.persistence.Column;
import ru.kolivim.estimates.calculator.domain.estimate.Element;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

import java.util.UUID;

public interface ElementRepository extends BaseRepository<Element> {

    Boolean existsByWorksIdAndRevision(UUID worksId, String revision);
    Boolean existsByWorksId(UUID worksId);
    Boolean existsByGroup(Integer group);

}