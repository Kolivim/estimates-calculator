package ru.kolivim.estimates.calculator.impl.resource.estimate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.estimates.calculator.api.dto.estimate.*;
import ru.kolivim.estimates.calculator.api.dto.estimate.info.EstimateInfo;
import ru.kolivim.estimates.calculator.api.resource.estimate.EstimateResource;
import ru.kolivim.estimates.calculator.impl.service.estimate.EstimateService;

import javax.security.auth.login.AccountException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EstimateResourceImpl implements EstimateResource {

    private final EstimateService estimateService;
    @Override
    public ResponseEntity<ElementDto> createElement(ElementDto elementDto) throws AccountException {
        return ResponseEntity.ok(estimateService.createElement(elementDto));
    }

    @Override
    public ResponseEntity<MaterialElementDto> createMaterialElement(MaterialElementDto materialElementDto) throws AccountException {
        return ResponseEntity.ok(estimateService.createMaterialElement(materialElementDto));
    }

    @Override
    public ResponseEntity<EstimateElementDto> createEstimateElement(EstimateElementDto estimateElementDto) throws AccountException {
        return ResponseEntity.ok(estimateService.createEstimateElement(estimateElementDto));
    }

    @Override
    public ResponseEntity<EstimateDto> createEstimate(EstimateDto estimateDto) throws AccountException {
        return ResponseEntity.ok(estimateService.createEstimate(estimateDto));
    }


    @Override
    public ResponseEntity<EstimateInfo> getEstimate(@PathVariable UUID id) {
        return ResponseEntity.ok(estimateService.getEstimate(id));
    }

    @Override
    public ResponseEntity<List<EstimateInfo>> getAllEstimates() {
        return ResponseEntity.ok(estimateService.getAllEstimates());
    }


}
