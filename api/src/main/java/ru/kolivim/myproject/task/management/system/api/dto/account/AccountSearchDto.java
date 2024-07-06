package ru.kolivim.myproject.task.management.system.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

import java.util.List;
import java.util.UUID;

@Data
public class AccountSearchDto extends BaseDto {

    @Schema(description = "Список id пользователей, запрашиваемых в запросе")
    private List<UUID> ids;
    @Schema(description = "Список id заблокированных пользователей")
    private List<UUID> blockedByIds;
    private String author;
    @Schema(description = "Имя пользователя")
    private String firstName;
    @Schema(description = "Фамилия пользователя")
    private String lastName;
    private String city;
    private String country;
    @Schema(description = "Email")
    private String email;
    @Schema(description = "Метка блокировки пользователя")
    private Boolean isBlocked;
    private StatusCode statusCode;
    private Integer ageFrom;
    private Integer ageTo;

}
