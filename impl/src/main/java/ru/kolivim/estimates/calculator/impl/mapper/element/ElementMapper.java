package ru.kolivim.estimates.calculator.impl.mapper.element;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import ru.kolivim.estimates.calculator.api.dto.estimate.ElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.MaterialElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.Status;
import ru.kolivim.estimates.calculator.api.dto.price.PriceListTypeDto;
import ru.kolivim.estimates.calculator.domain.estimate.Element;
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

}
