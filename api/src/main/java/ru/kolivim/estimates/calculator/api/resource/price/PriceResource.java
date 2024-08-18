package ru.kolivim.estimates.calculator.api.resource.price;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.estimates.calculator.api.dto.price.PriceDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceListDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceListTypeDto;
import ru.kolivim.estimates.calculator.api.dto.user.UserDto;

import javax.security.auth.login.AccountException;

@RestController
@RequestMapping("api/v1/price")
@Tag(name = "Api сервиса расценок",
        description = "Сервис для создания, редактирования, получения, удаления расценок и их прайс-листов")
public interface PriceResource {

    @PostMapping()
    public ResponseEntity<PriceDto> createPrice(@RequestBody PriceDto priceDto) throws AccountException /*PriceException*/ ;
    @PostMapping("/pricelist")
    ResponseEntity<PriceListDto> createPriceList(@RequestBody PriceListDto priceListDto) throws AccountException;
    @PostMapping("/pricelisttype")
    ResponseEntity<PriceListTypeDto> createPriceListType(@RequestBody PriceListTypeDto priceListTypeDto) throws AccountException;
}
