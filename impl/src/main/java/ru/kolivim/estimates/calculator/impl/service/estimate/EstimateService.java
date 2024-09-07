package ru.kolivim.estimates.calculator.impl.service.estimate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolivim.estimates.calculator.api.dto.estimate.ElementDto;
import ru.kolivim.estimates.calculator.api.dto.estimate.MaterialElementDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceDto;
import ru.kolivim.estimates.calculator.domain.estimate.Element;
import ru.kolivim.estimates.calculator.domain.estimate.MaterialElement;
import ru.kolivim.estimates.calculator.impl.exception.ElementException;
import ru.kolivim.estimates.calculator.impl.exception.MaterialElementException;
import ru.kolivim.estimates.calculator.impl.exception.PriceException;
import ru.kolivim.estimates.calculator.impl.mapper.element.ElementMapper;
import ru.kolivim.estimates.calculator.impl.repository.element.ElementRepository;
import ru.kolivim.estimates.calculator.impl.repository.element.MaterialElementRepository;
import ru.kolivim.estimates.calculator.impl.repository.price.PriceListRepository;
import ru.kolivim.estimates.calculator.impl.repository.price.PriceRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EstimateService {

    private final ElementMapper elementMapper;

    private final ElementRepository elementRepository;
    private final PriceRepository priceRepository;
    private final PriceListRepository priceListRepository;
    private final MaterialElementRepository materialElementRepository;



    /** Ниже по estimates: */


    /** Ниже по ??? таблице - для связи estimates с elements и указания количества работ, указанных
        в elements, по которым уже можно посчитать саму смету: */


    /** Ниже по elements: */

    public ElementDto createElement(ElementDto elementDto) throws ElementException {
        log.info("EstimateService:createElement(ElementDto elementDto) startMethod, ElementDto: {}", elementDto);
        elementDto.setIsDeleted(false);
        getCheksAllElementInfo(elementDto);

        Integer addLastWorkVersion = getLastWorkVersion(elementDto);
        addLastWorkVersion++;
        elementDto.setVersion(addLastWorkVersion);

        Element element = save(elementMapper.toElement(elementDto));
        return elementMapper.toElementDto(element);
    }

    private Integer getLastWorkVersion(ElementDto elementDto) {
        log.info("EstimateService:getLastVersion() startMethod, получен ElementDto: {}",  elementDto);
        Integer lastWorkVersion = elementRepository.lastWorkVersion(elementDto.getWorksId(), elementDto.getRevision());

        if (lastWorkVersion == null) {lastWorkVersion = 0;}
        log.info("EstimateService:getLastVersion() endMethod, получен lastWorkVersion = {}", lastWorkVersion);
        return lastWorkVersion;
    }

    private void getCheksAllElementInfo(ElementDto elementDto) {
        log.info("EstimateService:getCheksAllElementInfo(ElementDto elementDto) startMethod, ElementDto: {}", elementDto);
        getErrorIfNull(elementDto);
        getErrorIfElementExist(elementDto);       //  worksId*  revision*
        getErrorIfPriceListsInfoNotValid(elementDto); // workPriceListId***  materialPriceListId***
        getErrorIfElementInfoNotValid(elementDto);     // status** // note: parentId**  group**
    }



    private Element save(Element element) {
        log.info("EstimateService:save(Element element) startMethod, Element: {}", element);
        return elementRepository.save(element);
    }

    private void getErrorIfNull(Object object) {
        log.info("EstimateService:getErrorIfNull(Object object) startMethod, Object: {}", object);
        if ((object == null)) {
            throw new ElementException("Не получены данные element");
        }
    }

    private void getErrorIfElementExist(ElementDto elementDto) {
        log.info("EstimateService:getErrorIfElementExist(ElementDto elementDto) startMethod, ElementDto: {}", elementDto);
        if (elementRepository.existsByWorksIdAndRevision(elementDto.getWorksId(), elementDto.getRevision())) {
            throw new ElementException("Element с переданным WorksId и Revision уже существует. Выберите другую Revision");
        }
    }

    private void getErrorIfPriceListsInfoNotValid(ElementDto elementDto) {
        log.info("EstimateService:getErrorIfPriceListsInfoNotValid(ElementDto elementDto) startMethod, ElementDto: {}", elementDto);

        if (elementDto.getWorkPriceListId() != null) {
            if (!priceListRepository.existsById(elementDto.getWorkPriceListId())) {
                throw new ElementException("Переданный WorkPriceList не существует");
            }
        }

        if (elementDto.getMaterialPriceListId() != null) {
            if (!priceListRepository.existsById(elementDto.getMaterialPriceListId())) {
                throw new ElementException("Переданный MaterialPriceList не существует");
            }
        }

    }


    private void getErrorIfElementInfoNotValid(ElementDto elementDto) {
        log.info("EstimateService:getErrorIfElementInfoNotValid(ElementDto elementDto) startMethod, ElementDto: {}", elementDto);

        if (elementDto.getParentId() != null) {
            if(!elementRepository.existsByWorksId(elementDto.getParentId())) {
                throw new ElementException("Переданный ParentId не существует");
            }
        }

        /* //TODO: Сделать табличку с группами и с нее брать существует ли группа !!!
        if (elementDto.getGroup() != null) {
            if(elementRepository.existsByGroup(elementDto.getGroup())) {
                throw new ElementException("Переданной Group не существует");
            }
        }
        */

    }


    /** Ниже по material_elements: */

    public MaterialElementDto createMaterialElement(MaterialElementDto materialElementDto) {
        log.info("EstimateService{}: createMaterialElement(MaterialElementDto materialElementDto) startMethod, MaterialElementDto: {}",
                materialElementDto);

        materialElementDto.setIsDeleted(false);
        getCheksAllMaterialElementInfo(materialElementDto);

        MaterialElement materialElement = save(elementMapper.toMaterialElement(materialElementDto));

        return elementMapper.toMaterialElementDto(materialElement);
    }

    private void getCheksAllMaterialElementInfo(MaterialElementDto materialElementDto) {
        log.info("EstimateService:getCheksAllMaterialElementInfo(MaterialElementDto materialElementDto) startMethod, MaterialElementDto: {}", materialElementDto);
        getErrorIfNull(materialElementDto);
        getErrorIfReferencesExist(materialElementDto);  //  elementId* materialId*
        getErrorIfMaterialElementInfoNotValidAndSetSomeAttr(materialElementDto);

        }

    private void getErrorIfMaterialElementInfoNotValidAndSetSomeAttr(MaterialElementDto materialElementDto) {
        log.info("EstimateService:getErrorIfMaterialElementInfoNotValid(MaterialElementDto materialElementDto) startMethod, MaterialElementDto: {}", materialElementDto);

        if (materialElementDto.getQuantity() != null) {
            if(materialElementDto.getQuantity() < 0) {
                throw new MaterialElementException("MaterialElement передан с Quantity < 0. Введите Quantity > 0");
            }
        } else {
            throw new MaterialElementException("MaterialElement передан с пустым Quantity. Введите Quantity");
        }

        if (materialElementDto.getConsumptionRate() != null) {
            if(materialElementDto.getConsumptionRate() < 0) {
                throw new MaterialElementException("MaterialElement передан с ConsumptionRate < 0. Введите ConsumptionRate > 0");
            }
        } else {
            /** Приравниваем к 1 */
            materialElementDto.setConsumptionRate(1.0);
            //throw new MaterialElementException("MaterialElement передан с пустым ConsumptionRate. Введите ConsumptionRate");
        }

        if (materialElementDto.getReductionFactor() != null) {
            if(materialElementDto.getReductionFactor() < 0) {
                throw new MaterialElementException("MaterialElement передан с ReductionFactor < 0. Введите ReductionFactor > 0");
            }
        } else {
            /** Приравниваем к 1 */
            materialElementDto.setReductionFactor(1.0);
            //throw new MaterialElementException("MaterialElement передан с пустым ConsumptionRate. Введите ConsumptionRate");
        }

        if (materialElementDto.getNumberLayers() != null) {
            if(materialElementDto.getNumberLayers() < 0) {
                throw new MaterialElementException("MaterialElement передан с NumberLayers < 0. Введите NumberLayers > 0");
            }
        } else {
            /** Приравниваем к 1 */
            materialElementDto.setNumberLayers(1.0);
            //throw new MaterialElementException("MaterialElement передан с пустым ConsumptionRate. Введите ConsumptionRate");
        }

        if (materialElementDto.getLayerThickness() != null) {
            if(materialElementDto.getLayerThickness() < 0) {
                throw new MaterialElementException("MaterialElement передан с LayerThickness < 0. Введите LayerThickness > 0");
            }
        } else {
            /** Приравниваем к 1 */
            materialElementDto.setLayerThickness(1.0);
           //throw new MaterialElementException("MaterialElement передан с пустым ConsumptionRate. Введите ConsumptionRate");
        }

        log.info("EstimateService:getErrorIfMaterialElementInfoNotValid(MaterialElementDto materialElementDto) endMethod, MaterialElementDto: {}", materialElementDto);

        //TODO: Добавить проверку что в materialId передан именно UUID Материала (materialId), а не работы (workId)
    }

    private void getErrorIfReferencesExist(MaterialElementDto materialElementDto) {
        log.info("EstimateService:getErrorIfReferencesExist(MaterialElementDto materialElementDto) startMethod, MaterialElementDto: {}", materialElementDto);
        if (materialElementDto.getElementId() != null) {
            if (! elementRepository.existsById(materialElementDto.getElementId())) {
                throw new MaterialElementException("MaterialElement с переданным elementId не существует. Выберите другой elementId");
            }
        } else {
            throw new MaterialElementException("MaterialElement передан с пустым elementId. Выберите elementId");
        }

        if (materialElementDto.getMaterialId() != null) {
            if (! priceRepository.existsById(materialElementDto.getMaterialId())) {
                throw new MaterialElementException("MaterialElement с переданным materialId не существует. Выберите другой materialId");
            }
        } else {
            throw new MaterialElementException("MaterialElement передан с пустым materialId. Выберите materialId");
        }
    }

    private MaterialElement save(MaterialElement materialElement) {
        log.info("EstimateService:save(MaterialElement materialElement) startMethod, MaterialElement: {}", materialElement);
        return materialElementRepository.save(materialElement);
    }

}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    public ElementDto elementsMaterial(ElementDto elementDto) throws ElementException {
        log.info("EstimateService:elementsMaterial(ElementDto elementDto) startMethod, ElementDto: {}", elementDto);
        elementDto.setIsDeleted(false);
        getCheksAllElementInfo(elementDto);

        Integer addLastWorkVersion = getLastWorkVersion(elementDto);
        addLastWorkVersion++;
        elementDto.setVersion(addLastWorkVersion);

        Element element = save(elementMapper.toElement(elementDto));
        return elementMapper.toElementDto(element);
    }
    */