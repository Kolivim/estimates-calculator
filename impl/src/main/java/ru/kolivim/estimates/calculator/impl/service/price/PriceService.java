package ru.kolivim.estimates.calculator.impl.service.price;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolivim.estimates.calculator.api.dto.price.PriceDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceListDto;
import ru.kolivim.estimates.calculator.domain.price.Price;
import ru.kolivim.estimates.calculator.domain.price.PriceList;
import ru.kolivim.estimates.calculator.impl.exception.PriceException;
import ru.kolivim.estimates.calculator.impl.exception.PriceListException;
import ru.kolivim.estimates.calculator.impl.exception.UserException;
import ru.kolivim.estimates.calculator.impl.mapper.price.PriceMapper;
import ru.kolivim.estimates.calculator.impl.repository.price.PriceListRepository;
import ru.kolivim.estimates.calculator.impl.repository.price.PriceRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PriceService {

//    private final AccountService accountService;
    private final PriceMapper priceMapper;

    private final PriceListRepository priceListRepository;
    private final PriceRepository priceRepository;


    /** TODO: Может переписать на компоненты??? */

    /** Ниже по Price-list: */

    public PriceListDto createPriceList(PriceListDto priceListDto) throws PriceException {
        log.info("PriceService:createPriceList(PriceListDto priceListDto) startMethod, PriceListDto: {}", priceListDto);
        getCheksAllPriceListInfo(priceListDto);
        PriceList priceList = save(priceMapper.toPriceList(priceListDto));
        return priceMapper.toPriceListDto(priceList);
    }

    private void getCheksAllPriceListInfo(PriceListDto priceListDto) {
        log.info("PriceService:getCheksAllPriceListInfo(PriceListDto priceListDto) startMethod, PriceListDto: {}", priceListDto);
        getErrorIfNull(priceListDto);
        getErrorIfNamePriceListExist(priceListDto);
        getErrorIfPriceListTypeNotExist(priceListDto);
    }

    private void getErrorIfNamePriceListExist(PriceListDto priceListDto) {
        log.info("PriceService:getErrorIfNamePriceListExist(PriceListDto priceListDto) startMethod, PriceListDto: {}", priceListDto);
        if (priceListRepository.existsByName(priceListDto.getName())) {
            throw new PriceListException("PriceList с таким именем уже существует");
        }
    }

    private void getErrorIfPriceListTypeNotExist(PriceListDto priceListDto) {
        log.info("PriceService:getErrorIfPriceListTypeNotExist(PriceListDto priceListDto) startMethod, PriceListDto: {}", priceListDto);
//        if (userRepository.existsByLogin(userDto.getLogin())) {
//            throw new UserException("Пользователь с таким login уже существует");   // TODO: Исправить проверку на валидную
//        }
    }

    private PriceList save(PriceList priceList) {
        log.info("PriceService:save(PriceList priceList) startMethod, PriceList: {}", priceList);
        return priceListRepository.save(priceList);
    }

    /** Ниже по Price: */

    public PriceDto createPrice(PriceDto priceDto) throws PriceException {
        log.info("PriceService:create(PriceDto priceDto) startMethod, PriceDto: {}", priceDto);
        getCheksAllPriceInfo(priceDto);
        Price price = save(priceMapper.toPrice(priceDto));
        return priceMapper.toPriceDto(price);
    }

    private Price save(Price price) {
        log.info("PriceService:save(Price price) startMethod, Price: {}", price);
        return priceRepository.save(price);
    }

    private void getCheksAllPriceInfo(PriceDto priceDto) {
        log.info("PriceService:getCheksAllPriceInfo(PriceDto priceDto) startMethod, PriceDto: {}", priceDto);
        getErrorIfNull(priceDto);
        getErrorIfPriceListNotExist(priceDto);
        getErrorIfPriceInfoNotValid(priceDto);
    }

    private void getErrorIfNull(Object object) {
        log.info("PriceService:getErrorIfNull(Object object) startMethod, Object: {}", object);
        if ((object == null)) {
            throw new PriceException("Не получены данные расценки");
        }
    }

    private void getErrorIfPriceListNotExist(PriceDto priceDto) {
        log.info("PriceService:getErrorIfPriceListNotExist(PriceDto priceDto) startMethod, PriceDto: {}", priceDto);
        if (!priceListRepository.existsById(priceDto.getPriceListId())) {
            throw new PriceListException("PriceList с таким UUID не существует");
        }
    }

    private void getErrorIfPriceInfoNotValid(PriceDto priceDto) {
        log.info("PriceService:getErrorIfPriceInfoNotValid(PriceDto priceDto) startMethod, PriceDto: {}", priceDto);
        if (priceRepository.existsByNameAndPriceListId(priceDto.getName(), priceDto.getPriceListId())) {
            throw new PriceException("Расценка с именем: " + priceDto.getName() +
                    " в прайс-листе с UUID: " + priceDto.getPriceListId() + " - уже существует");
        }
    }

}

                                            /* PriceDto:
                                            UUID priceListId;
                                            String name;
                                            Double price;
                                            String description;
                                            */