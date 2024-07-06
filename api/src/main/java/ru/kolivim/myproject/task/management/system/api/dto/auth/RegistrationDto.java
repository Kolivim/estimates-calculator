package ru.kolivim.myproject.task.management.system.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto extends BaseDto {

    @Schema(description = "Дата рождения пользователя")
    ZonedDateTime birthDate;

    @Schema(description = "ФИО пользователя")
    private String fullName;

    @Schema(description = "Логин")
    private String login;                                        /** Проверить что свободен */

    @Schema(description = "Пароль")
    private String password;

    @Schema(description = "Номер телефона пользователя")
    private String phone;                                        /** Проверить что свободен */

    @Schema(description = "Email пользователя")
    private String email;                                        /** Проверить что свободен */



    @Schema(description = "Изначальная сумма на счете")
    private Double balance;



    /** Отключить в дальнейшем */

    @Schema(description = "Имя пользователя")
    private String firstName;
    @Schema(description = "Фамилия пользователя")
    private String lastName;
    @Schema(description = "Пароль")
    private String password1;
    @Schema(description = "Повторение пароля")
    private String password2;

    private String captchaCode;
    private String captchaSecret;
}


