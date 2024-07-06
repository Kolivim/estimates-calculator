package ru.kolivim.myproject.task.management.system.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class RolesDto {
    @Schema(description = "Идентификатор")
    private UUID id;
    @Schema(description = "Роль")
    private String role;

}
