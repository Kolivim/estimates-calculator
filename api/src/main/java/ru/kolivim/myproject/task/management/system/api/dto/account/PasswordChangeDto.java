package ru.kolivim.myproject.task.management.system.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeDto {

    @Schema(description = "Новый пароль")
    private String newPassword1;
    @Schema(description = "Повтор нового пароля")
    private String newPassword2;
}
