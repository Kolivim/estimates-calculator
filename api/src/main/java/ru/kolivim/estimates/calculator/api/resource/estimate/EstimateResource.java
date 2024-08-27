package ru.kolivim.estimates.calculator.api.resource.estimate;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.estimates.calculator.api.dto.estimate.ElementDto;
import ru.kolivim.estimates.calculator.api.dto.price.PriceDto;

import javax.security.auth.login.AccountException;

@RestController
@RequestMapping("api/v1/estimate")
@Tag(name = "Api сервиса смет",
        description = "Сервис для создания, редактирования, получения, удаления смет и их составных частей")
public interface EstimateResource {

    @PostMapping("/element")
    public ResponseEntity<ElementDto> createElement(@RequestBody ElementDto elementDto) throws AccountException /*ElementException*/ ;

}
