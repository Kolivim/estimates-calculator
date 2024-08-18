package ru.kolivim.estimates.calculator.impl.mapper.price;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import ru.kolivim.estimates.calculator.api.dto.price.PriceDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceListDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceListTypeDto;
import ru.kolivim.estimates.calculator.domain.price.Price;
import ru.kolivim.estimates.calculator.domain.price.PriceList;
import ru.kolivim.estimates.calculator.domain.price.PriceListType;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PriceMapper {
    private static final UUID SYSTEM_UUID = UUID.fromString("2788e7e3-9e0c-47ea-8798-e987c7d9ab4f");

    public PriceListType toPriceListType(PriceListTypeDto priceListTypeDto) {
        log.info("PriceMapper:toPriceListType(PriceListTypeDto priceListTypeDto) startMethod - получен PriceListTypeDto: {}",
                priceListTypeDto);

        PriceListType priceListType = new PriceListType();
        priceListType.setIsDeleted(false);
        priceListType.setName(priceListTypeDto.getName());
        priceListType.setType(priceListTypeDto.getType()); /** Берем последнюю запись type в таблице и прибавляем 1 */
        priceListType.setDescription(priceListTypeDto.getDescription());
        priceListType.setLastAuthorId(SYSTEM_UUID); // TODO: Исправить и внести корректный юид вносящего позицию вместо системного
        priceListType.setLastModifiedDate(ZonedDateTime.now());
        priceListType.setVersion(1);

        log.info("PriceMapper:toPriceListType(PriceListTypeDto priceListTypeDto) endMethod - получен к возврату PriceListType: {}",
                priceListType);
        return priceListType;
    }


    public PriceListTypeDto toPriceListTypeDto(PriceListType priceListType) {
        log.info("PriceMapper:toPriceListTypeDto(PriceListType priceListType) startMethod - получен PriceListType: {}",
                priceListType);

        PriceListTypeDto priceListTypeDto = new PriceListTypeDto();
        priceListTypeDto.setDescription(priceListType.getDescription());
        priceListTypeDto.setType(priceListType.getType());
        priceListTypeDto.setName(priceListType.getName());

        log.info("PriceMapper:toPriceListTypeDto(PriceListType priceListType) startMethod - получен к возврату PriceListTypeDto: {}",
                priceListTypeDto);
        return priceListTypeDto;
    }

    public PriceList toPriceList(PriceListDto priceListDto) {
        log.info("PriceMapper:toPriceList(PriceListDto priceListDto) startMethod - получен PriceListDto: {}", priceListDto);

        PriceList priceList = new PriceList();
        priceList.setIsDeleted(false);
        priceList.setName(priceListDto.getName());
        priceList.setType(priceListDto.getType());
        priceList.setDescription(priceListDto.getDescription());
        priceList.setLastAuthorId(SYSTEM_UUID); // TODO: Исправить и внести корректный юид вносящего позицию вместо системного
        priceList.setLastModifiedDate(ZonedDateTime.now());
        priceList.setVersion(1);

        log.info("PriceMapper:toPriceList(PriceListDto priceListDto) endMethod - получен к возврату PriceList: {}", priceList);
        return priceList;
    }

    public PriceListDto toPriceListDto(PriceList priceList) {
        log.info("PriceMapper:toPriceListDto(PriceList priceList) startMethod - получен PriceList: {}", priceList);

        PriceListDto priceListDto = new PriceListDto();
        priceListDto.setName(priceList.getName());
        priceListDto.setType(priceList.getType());
        priceListDto.setDescription(priceList.getDescription());

        log.info("PriceMapper:toPriceListDto(PriceList priceList) endMethod - получен к возврату PriceListDto: {}", priceListDto);
        return priceListDto;
    }

    public Price toPrice(PriceDto priceDto) {
        log.info("PriceMapper:toPrice(PriceDto priceDto) startMethod - получен PriceDto: {}", priceDto);

        Price price = new Price();
        price.setIsDeleted(false);
        price.setPriceListId(priceDto.getPriceListId());
        price.setName(priceDto.getName());
        price.setPrice(priceDto.getPrice());
        price.setDescription(priceDto.getDescription());
        price.setLastAuthorId(SYSTEM_UUID); // TODO: Исправить и внести корректный юид вносящего позицию вместо системного
        price.setLastModifiedDate(ZonedDateTime.now());
        price.setVersion(1);

        log.info("PriceMapper:toPrice(PriceDto priceDto) endMethod - получен к возврату Price: {}", price);
        return price;
    }

    public PriceDto toPriceDto(Price price) {
        log.info("PriceMapper:toPriceDto(Price price) startMethod - получен Price: {}", price);

        PriceDto priceDto = new PriceDto();
        priceDto.setPriceListId(price.getPriceListId());
        priceDto.setName(price.getName());
        priceDto.setPrice(price.getPrice());
        priceDto.setDescription(price.getDescription());

        log.info("PriceMapper:toPriceDto(Price price) endMethod - получен к возврату PriceDto: {}", priceDto);
        return  null;
    }

    /**
     PriceDto:
     UUID priceListId;
     String name;
     Double price;
     String description;
     */

    /**
     Price:
     private UUID priceListId;
     private String name;
     private Double price;
     private String description;
     private UUID lastAuthorId;
     private ZonedDateTime lastModifiedDate;
     private Integer version;
     */

}
