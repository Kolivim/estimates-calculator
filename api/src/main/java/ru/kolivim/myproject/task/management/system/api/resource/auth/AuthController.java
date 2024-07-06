package ru.kolivim.myproject.task.management.system.api.resource.auth;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.auth.*;

@Controller
@RequestMapping("api/v1/auth/")
@Tag(name = "Api сервиса аутентификации",
        description = "Сервис для регистрации, логина и их окружения")
public interface AuthController {

    @Operation(summary = "Аутентификация",
            description = "Вход в систему и последующее получение активных access и refresh токенов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Аутентификация прошла упешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticateResponseDto.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Аутентификация не удалась. Не верный логин или пароль",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticateResponseDto> login(@RequestBody AuthenticateDto authenticateDto);


    @Operation(summary = "Регистрация",
            description = "Регистрация нового аккаунта пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Регистрация прошла упешно"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Регистрация не удалась. Существует пользователь с таким email",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationDto loginDto);

    @Operation(summary = "Выход из системы",
            description = "Выход из системы и удаление токенов из списка активных, " +
                    "для текущего авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Выход из системы успешно выполнен")
    })
    @PostMapping("/logout")
    public ResponseEntity<String> logout();


    @Operation(summary = "Восстановление пароля (через e-mail)",
            description = "Запрос на отправку письма на e-mail со ссылкой на страницу восстановление пароля")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Письмо на почту отправлено"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Нет пользователя с указанной почтой")
    })
    @PostMapping("/password/recovery/")
    public ResponseEntity<String> sendRecoveryEmail(@RequestBody PasswordRecoveryDto recoveryDto);


    @Operation(summary = "Восстановление пароля",
            description = "Восстановление пароля по ссылке из почты. В качестве ссылки направлялся id токена " +
                    "восстановления, который добавлен в базу при отправке сообщения на e-mail")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пароль успешно изменён"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неверная или просроченная ссылка")
    })
    @PostMapping("/password/recovery/{linkId}")
    public ResponseEntity<String> changePassword(
            @Parameter(description = "id токена восстановления в БД") @PathVariable String linkId,
            @RequestBody NewPasswordDto passwordDto);


    @GetMapping("/captcha")
    public ResponseEntity<CaptchaDto> getCaptcha();


    /*
    @Operation(summary = "Изменение пароля",
            description = "Изменение пароля существующего активного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пароль успешно изменён",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Введённые пароли не совпадают",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/change-password-link")
    ResponseEntity<AccountDto> changePasswordLink(@RequestBody PasswordChangeDto newAggregateEmailDto);

    @Operation(summary = "Изменение почты",
            description = "Изменение почты существующего активного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Почта успешно изменена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    })
    })
    @PostMapping("/change-email-link")
    ResponseEntity<AccountDto> changeEmailLink(@RequestBody ChangeEmailDto newAggregateEmailDto);
    */

}
