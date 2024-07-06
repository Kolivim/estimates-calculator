package ru.kolivim.myproject.task.management.system.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDto{

//    @Schema(description = "email пользователя")
//    private String email;

    @Schema(description = "Логин пользователя")
    private String login;
    @Schema(description = "Пароль пользователя")
    private String password;
}
