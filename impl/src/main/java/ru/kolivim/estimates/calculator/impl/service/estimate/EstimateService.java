package ru.kolivim.estimates.calculator.impl.service.estimate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolivim.estimates.calculator.api.dto.estimate.ElementDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceDto;
import ru.kolivim.estimates.calculator.domain.estimate.Element;
import ru.kolivim.estimates.calculator.impl.exception.ElementException;
import ru.kolivim.estimates.calculator.impl.exception.PriceException;
import ru.kolivim.estimates.calculator.impl.mapper.element.ElementMapper;
import ru.kolivim.estimates.calculator.impl.repository.element.ElementRepository;
import ru.kolivim.estimates.calculator.impl.repository.price.PriceListRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EstimateService {

    private final ElementMapper elementMapper;

    private final ElementRepository elementRepository;
    private final PriceListRepository priceListRepository;



    /** Ниже по estimates: */


    /** Ниже по ??? таблице - для связи estimates с elements и указания количества работ, указанных
        в elements, по которым уже можно посчитать саму смету: */


    /** Ниже по elements: */

    public ElementDto createElement(ElementDto elementDto) throws ElementException {
        log.info("EstimateService:createElement(ElementDto elementDto) startMethod, ElementDto: {}", elementDto);
        elementDto.setIsDeleted(false);
        getCheksAllElementInfo(elementDto);
        Element element = save(elementMapper.toElement(elementDto));
        return elementMapper.toElementDto(element);
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



}
