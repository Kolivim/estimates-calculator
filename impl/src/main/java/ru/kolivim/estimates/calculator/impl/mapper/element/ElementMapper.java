package ru.kolivim.estimates.calculator.impl.mapper.element;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import ru.kolivim.estimates.calculator.api.dto.estimate.*;
import ru.kolivim.estimates.calculator.api.dto.price.PriceListTypeDto;
import ru.kolivim.estimates.calculator.domain.estimate.Element;
import ru.kolivim.estimates.calculator.domain.estimate.Estimate;
import ru.kolivim.estimates.calculator.domain.estimate.EstimateElement;
import ru.kolivim.estimates.calculator.domain.estimate.MaterialElement;
import ru.kolivim.estimates.calculator.domain.price.PriceListType;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ElementMapper {

    private static final UUID SYSTEM_UUID = UUID.fromString("2788e7e3-9e0c-47ea-8798-e987c7d9ab4f");


    /** Ниже по elements: */

    public Element toElement(ElementDto elementDto) {
        log.info("ElementMapper:toElement(ElementDto elementDto) startMethod - получен ElementDto: {}", elementDto);

        Element element = new Element();
        element.setIsDeleted(false);
        element.setWorksId(elementDto.getWorksId());
        element.setRevision(elementDto.getRevision());
        element.setIsParent(elementDto.getIsParent());
        element.setParentId(elementDto.getParentId());
        element.setGroup(elementDto.getGroup());
        element.setWorkPriceListId(elementDto.getWorkPriceListId());
        element.setMaterialPriceListId(elementDto.getMaterialPriceListId());
        element.setDescription(elementDto.getDescription());
        element.setLastAuthorId(SYSTEM_UUID);   // TODO: Исправить и внести корректный юид вносящего позицию вместо системного
        element.setLastModifiedDate(ZonedDateTime.now());
        element.setVersion(elementDto.getVersion());
        element.setStatus(elementDto.getStatus());

        log.info("ElementMapper:toElement(ElementDto elementDto) endMethod - получен к возврату Element: {}", element);
        return element;
    }

    public ElementDto toElementDto(Element element) {
        log.info("ElementMapper:toElementDto(Element element) startMethod - получен Element: {}", element);

        ElementDto elementDto = new ElementDto();
        elementDto.setIsDeleted(element.getIsDeleted());
        elementDto.setWorksId(element.getWorksId());
        elementDto.setRevision(element.getRevision());
        elementDto.setIsParent(element.getIsParent());
        elementDto.setParentId(element.getParentId());
        elementDto.setGroup(element.getGroup());
        elementDto.setWorkPriceListId(element.getWorkPriceListId());
        elementDto.setMaterialPriceListId(element.getMaterialPriceListId());
        elementDto.setDescription(element.getDescription());
        elementDto.setLastAuthorId(element.getLastAuthorId()); // TODO: Может заменить на имя, для вывода? Либо добавить поле с именем?
        elementDto.setLastModifiedDate(element.getLastModifiedDate());
        elementDto.setVersion(element.getVersion());
        elementDto.setStatus(element.getStatus());

        log.info("ElementMapper:toElementDto(Element element) endMethod - получен к возврату ElementDto: {}", elementDto);
        return elementDto;
    }


    /** Ниже по material_element: */

    public MaterialElement toMaterialElement(MaterialElementDto materialElementDto) {
        log.info("ElementMapper:toMaterialElement(MaterialElementDto materialElementDto) startMethod - получен MaterialElementDto: {}", materialElementDto);

        MaterialElement materialElement = new MaterialElement();
        materialElement.setIsDeleted(materialElementDto.getIsDeleted());
        materialElement.setElementId(materialElementDto.getElementId());
        materialElement.setMaterialId(materialElementDto.getMaterialId());
        materialElement.setQuantity(materialElementDto.getQuantity());
        materialElement.setConsumptionRate(materialElementDto.getConsumptionRate());
        materialElement.setReductionFactor(materialElementDto.getReductionFactor());
        materialElement.setNumberLayers(materialElementDto.getNumberLayers());
        materialElement.setLayerThickness(materialElementDto.getLayerThickness());
        materialElement.setDescription(materialElementDto.getDescription());
        materialElement.setLastAuthorId(SYSTEM_UUID);   // TODO: Исправить и внести корректный юид вносящего позицию вместо системного
        materialElement.setLastModifiedDate(ZonedDateTime.now());

        log.info("ElementMapper:toMaterialElement(MaterialElementDto materialElementDto) endMethod - получен к возврату MaterialElement: {}", materialElement);

        return materialElement;
    }


    public MaterialElementDto toMaterialElementDto(MaterialElement materialElement) {
        log.info("ElementMapper:toMaterialElementDto(MaterialElement materialElement) startMethod - получен MaterialElement: {}", materialElement);

        MaterialElementDto materialElementDto = new MaterialElementDto();
        materialElementDto.setIsDeleted(materialElement.getIsDeleted());
        materialElementDto.setElementId(materialElement.getElementId());
        materialElementDto.setMaterialId(materialElement.getMaterialId());
        materialElementDto.setQuantity(materialElement.getQuantity());
        materialElementDto.setConsumptionRate(materialElement.getConsumptionRate());
        materialElementDto.setReductionFactor(materialElement.getReductionFactor());
        materialElementDto.setNumberLayers(materialElement.getNumberLayers());
        materialElementDto.setLayerThickness(materialElement.getLayerThickness());
        materialElementDto.setDescription(materialElement.getDescription());
        materialElementDto.setLastAuthorId(materialElement.getLastAuthorId());
        materialElementDto.setLastModifiedDate(materialElement.getLastModifiedDate());

        log.info("ElementMapper:toMaterialElementDto(MaterialElement materialElement) endMethod - получен к возврату MaterialElementDto: {}", materialElementDto);

        return materialElementDto;
    }

    public EstimateElement toEstimateElement(EstimateElementDto estimateElementDto) {
        log.info("ElementMapper:toEstimateElement(EstimateElementDto estimateElementDto) startMethod - получен EstimateElementDto: {}", estimateElementDto);

        EstimateElement estimateElement = new EstimateElement();
        estimateElement.setIsDeleted(estimateElementDto.getIsDeleted());
        estimateElement.setEstimateId(estimateElementDto.getEstimateId());
        estimateElement.setElementId(estimateElementDto.getElementId());
        estimateElement.setQuantity(estimateElementDto.getQuantity());
        estimateElement.setWorkPriceListId(estimateElementDto.getWorkPriceListId());
        estimateElement.setMaterialPriceListId(estimateElementDto.getMaterialPriceListId());
        estimateElement.setDescription(estimateElementDto.getDescription());
        estimateElement.setLastAuthorId(SYSTEM_UUID);   // TODO: Исправить и внести корректный юид вносящего позицию вместо системного
        estimateElement.setLastModifiedDate(ZonedDateTime.now());
        estimateElement.setStatus(estimateElementDto.getStatus());

        log.info("ElementMapper:toEstimateElement(EstimateElementDto estimateElementDto) endMethod - получен к возврату EstimateElement: {}", estimateElement);
        return estimateElement;
    }

    public EstimateElementDto toEstimateElementDto(EstimateElement estimateElement) {
        log.info("ElementMapper:toEstimateElementDto(EstimateElement estimateElement) startMethod - получен EstimateElement: {}", estimateElement);

        EstimateElementDto estimateElementDto = new EstimateElementDto();
        estimateElementDto.setId(estimateElement.getId());
        estimateElementDto.setIsDeleted(estimateElement.getIsDeleted());
        estimateElementDto.setEstimateId(estimateElement.getEstimateId());
        estimateElementDto.setElementId(estimateElement.getElementId());
        estimateElementDto.setQuantity(estimateElement.getQuantity());
        estimateElementDto.setWorkPriceListId(estimateElement.getWorkPriceListId());
        estimateElementDto.setMaterialPriceListId(estimateElement.getMaterialPriceListId());
        estimateElementDto.setDescription(estimateElement.getDescription());
        estimateElementDto.setLastAuthorId(estimateElement.getLastAuthorId());
        estimateElementDto.setLastModifiedDate(estimateElement.getLastModifiedDate());
        estimateElementDto.setStatus(estimateElement.getStatus());

        log.info("ElementMapper:toEstimateElementDto(EstimateElement estimateElement) endMethod - получен к возврату EstimateElementDto: {}", estimateElementDto);
        return estimateElementDto;
    }

    public Estimate toEstimate(EstimateDto estimateDto) {
        log.info("ElementMapper:toEstimate(EstimateDto estimateDto) startMethod - получен EstimateDto: {}", estimateDto);

        Estimate estimate = new Estimate();
        estimate.setIsDeleted(estimateDto.getIsDeleted());
        estimate.setName(estimateDto.getName());
        estimate.setWorkPriceListId(estimateDto.getWorkPriceListId());
        estimate.setMaterialPriceListId(estimateDto.getMaterialPriceListId());
        estimate.setDescription(estimateDto.getDescription());
        estimate.setLastAuthorId(SYSTEM_UUID);   // TODO: Исправить и внести корректный юид вносящего позицию вместо системного
        estimate.setLastModifiedDate(ZonedDateTime.now());
        estimate.setVersion(estimateDto.getVersion());
        estimate.setStatus(estimateDto.getStatus());

        log.info("ElementMapper:toEstimate(EstimateDto estimateDto) endMethod - получен к возврату Estimate: {}", estimate);
        return estimate;
    }

    public EstimateDto toEstimateDto(Estimate estimate) {
        log.info("ElementMapper:toEstimateDto(Estimate estimate) startMethod - получен Estimate: {}", estimate);

        EstimateDto estimateDto = new EstimateDto();
        estimateDto.setId(estimate.getId());
        estimateDto.setIsDeleted(estimate.getIsDeleted());
        estimateDto.setName(estimate.getName());
        estimateDto.setWorkPriceListId(estimate.getWorkPriceListId());
        estimateDto.setMaterialPriceListId(estimate.getMaterialPriceListId());
        estimateDto.setDescription(estimate.getDescription());
        estimateDto.setLastAuthorId(estimate.getLastAuthorId());
        estimateDto.setLastModifiedDate(estimate.getLastModifiedDate());
        estimateDto.setVersion(estimate.getVersion());
        estimateDto.setStatus(estimate.getStatus());

        log.info("ElementMapper:toEstimateDto(Estimate estimate) endMethod - получен к возврату EstimateDto: {}", estimateDto);
        return estimateDto;
    }

    public EstimateInfo toEstimateInfo(Estimate estimate) {
        log.info("ElementMapper:toEstimateInfo(Estimate estimate) startMethod - получен Estimate: {}", estimate);

        EstimateInfo estimateInfo = new EstimateInfo();
        estimateInfo.setId(estimate.getId());
        estimateInfo.setIsDeleted(estimate.getIsDeleted());
        estimateInfo.setName(estimate.getName());
        estimateInfo.setWorkPriceListId(estimate.getWorkPriceListId());
        estimateInfo.setMaterialPriceListId(estimate.getMaterialPriceListId());
        estimateInfo.setDescription(estimate.getDescription());
        estimateInfo.setLastAuthorId(estimate.getLastAuthorId());
        estimateInfo.setLastModifiedDate(estimate.getLastModifiedDate());
        estimateInfo.setVersion(estimate.getVersion());
        estimateInfo.setStatus(estimate.getStatus());

        log.info("ElementMapper:toEstimateInfo(Estimate estimate)  endMethod - получен к возврату EstimateInfo: {}", estimateInfo);
        return estimateInfo;
    }
}
