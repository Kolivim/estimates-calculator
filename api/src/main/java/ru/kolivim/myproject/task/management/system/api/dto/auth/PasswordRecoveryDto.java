package ru.kolivim.myproject.task.management.system.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PasswordRecoveryDto {

    @Schema(description = "Email пользователя, доступ к аккаунту котороого надо восстановить")
    private String email;
}
