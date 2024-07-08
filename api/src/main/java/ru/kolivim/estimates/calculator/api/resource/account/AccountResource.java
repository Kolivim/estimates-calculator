package ru.kolivim.estimates.calculator.api.resource.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.estimates.calculator.api.dto.user.UserDto;

import javax.security.auth.login.AccountException;

@RestController
@RequestMapping("api/v1/account")
@Tag(name = "Api сервиса аккаунта",
        description = "Сервис для создания, редактирования, получения, удаления аккаунта")
public interface AccountResource {

    @Operation(summary = "Создание аккаунта",
            description = "Создание аккаунта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос.",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка.",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) throws AccountException;



}


