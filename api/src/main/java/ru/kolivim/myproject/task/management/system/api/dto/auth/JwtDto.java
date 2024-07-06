package ru.kolivim.myproject.task.management.system.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {

    @Schema(description = "id токена")
    private UUID id;
    @Schema(description = "id пользователя")
    private String userId;
    @Schema(description = "Login пользователя")
    private String login;
//    @Schema(description = "список ролей пользователя")
//    private List<String> roles;
}
