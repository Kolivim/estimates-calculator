package ru.kolivim.myproject.task.management.system.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NewPasswordDto {

    @Schema(description = "Новый пароль")
    private String password;
}
