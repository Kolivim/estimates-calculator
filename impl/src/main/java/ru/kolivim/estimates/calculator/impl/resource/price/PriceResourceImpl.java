package ru.kolivim.estimates.calculator.impl.resource.price;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.estimates.calculator.api.dto.price.PriceDto;
import ru.kolivim.estimates.calculator.api.resource.price.PriceResource;
import ru.kolivim.estimates.calculator.impl.service.price.PriceService;

import javax.security.auth.login.AccountException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PriceResourceImpl implements PriceResource {

    private final PriceService priceService;

    @Override
    public ResponseEntity<PriceDto> createPrice(PriceDto priceDto) throws AccountException {
        log.debug("PriceResourceImpl:createPrice(PriceDto priceDto) startMethod, PriceDto: {}", priceDto);
        return ResponseEntity.ok(priceService.createPrice(priceDto));
    }
}
