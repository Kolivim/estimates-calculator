package ru.kolivim.estimates.calculator.api.dto.user;

//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto extends BaseDto {

//    @Schema(description = "Идентификатор пользователя")
    private UUID id;

//    @Schema(description = "Метка удаления")
    private boolean isDeleted;

//    @Schema(description = "Имя пользователя")
    private String firstName;

    //    @Schema(description = "Отчество пользователя")
    private String middleName;

//    @Schema(description = "Фамилия пользователя")
    private String lastName;

//    @Schema(description = "Логин пользователя")
    private String login;

//    @Schema(description = "Пароль")
    private String password;


    /**
     private String firstName;
     private String middleName;
     private String lastName;
     private String login;
     private String password;
     */
}
