package ru.kolivim.estimates.calculator.impl.repository.element;

import ru.kolivim.estimates.calculator.api.dto.estimate.Status;
import ru.kolivim.estimates.calculator.domain.estimate.EstimateElement;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface EstimateElementRepository extends BaseRepository<EstimateElement> {

    List<EstimateElement> findByEstimateIdAndIsDeletedAndStatus (UUID estimateId, Boolean isDeleted, Status status);

}
