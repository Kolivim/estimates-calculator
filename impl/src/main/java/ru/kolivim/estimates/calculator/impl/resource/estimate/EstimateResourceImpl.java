package ru.kolivim.estimates.calculator.impl.resource.estimate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.estimates.calculator.api.dto.estimate.ElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.EstimateElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.MaterialElementDto;
import ru.kolivim.estimates.calculator.api.resource.estimate.EstimateResource;
import ru.kolivim.estimates.calculator.impl.service.estimate.EstimateService;

import javax.security.auth.login.AccountException;

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

}
