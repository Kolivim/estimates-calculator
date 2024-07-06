package ru.kolivim.myproject.task.management.system.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDataDTO {

    @Schema(description = "Номер телефона пользователя")
    private String phone;

    @Schema(description = "Email пользователя")
    private String email;

    @Schema(description = "Номер телефона пользователя, на замену приложенному")
    private String newPhone;

    @Schema(description = "Email пользователя, на замену приложенному")
    private String newEmail;

    @Schema(description = "Сумма для перевода")
    private Double sum;

    @Schema(description = "Логин пользователя, которому отправляется перевод")
    private String login;
}
