package ru.kolivim.estimates.calculator.api.resource.estimate;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.estimates.calculator.api.dto.estimate.*;

import javax.security.auth.login.AccountException;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/estimate")
@Tag(name = "Api сервиса смет",
        description = "Сервис для создания, редактирования, получения, удаления смет и их составных частей")
public interface EstimateResource {

    @PostMapping("/element")
    public ResponseEntity<ElementDto> createElement(@RequestBody ElementDto elementDto) throws AccountException /*ElementException*/ ;

    @PostMapping("/materialelement")
    public ResponseEntity<MaterialElementDto> createMaterialElement(@RequestBody MaterialElementDto materialElementDto) throws AccountException /*ElementException*/ ;

    @PostMapping("/estimateelement")
    public ResponseEntity<EstimateElementDto> createEstimateElement(@RequestBody EstimateElementDto estimateElementDto) throws AccountException /*ElementException*/ ;

    @PostMapping("")
    public ResponseEntity<EstimateDto> createEstimate(@RequestBody EstimateDto estimateDto) throws AccountException /*ElementException*/ ;

    @GetMapping("/{id}")
    public ResponseEntity<EstimateInfo> getEstimate(@PathVariable UUID id) throws AccountException;

}
