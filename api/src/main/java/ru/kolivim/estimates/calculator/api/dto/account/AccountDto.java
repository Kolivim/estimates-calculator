package ru.kolivim.estimates.calculator.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.estimates.calculator.api.dto.base.BaseDto;
import java.time.ZonedDateTime;

@Data
public class AccountDto extends BaseDto {

//    @Schema(description = "Время последнего входа в систему")
    private ZonedDateTime lastOnlineTime;

//    @Schema(description = "О пользователе")
    private String about;

//    @Schema(description = "Телефон")
    private String phone;

//    @Schema(description = "Email пользователя")
    private String email;

//    @Schema(description = "Должность пользователя")
    private String position;

//    @Schema(description = "Отдел пользователя")
    private String department;

//    @Schema(description = "Персональный номер пользователя")
    private String personnelNumber;

//    @Schema(description = "Время регистрации")
    private ZonedDateTime registrationDate;

//    @Schema(description = "Время последнего изменения аккаунта пользователя")
    private ZonedDateTime lastModifiedDate;


    /**
     private ZonedDateTime lastOnlineTime;
     private String about;
     private String phone;
     private String email;
     private String position;
     private String department;
     private String personnelNumber;
     private ZonedDateTime registrationDate;
     private ZonedDateTime lastModifiedDate;
     */
}
