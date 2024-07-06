package ru.kolivim.myproject.task.management.system.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

import java.time.LocalDateTime;

@Data
public class AccountDto extends BaseDto {

    @Schema(description = "Сумма на счете")
    private Double balance;

    @Schema(description = "Максимальная сумма, для начисления процентов")
    private Double maxBalance;

    //////////////////////////////////////////////////////////////////////////////////////////

//    @Schema(description = "Имя пользователя")
//    private String firstName;
//    @Schema(description = "Фамилия пользователя")
//    private String lastName;
//    @Schema(description = "Email")
//    private String email;
//    @Schema(description = "Пароль")
//    private String password;
//    @Schema(description = "Телефон")
//    private String phone;
//
//    private String photo;
//
//    private String profileCover;
//    @Schema(description = "О пользователе")
//    private String about;
//
//    private String city;
//
//    private String country;
//    private LocalDateTime regDate;
//
//    private LocalDateTime birthDate;
//
//    private String messagePermission;
//
//    private LocalDateTime lastOnlineTime;
//
//    private boolean isOnline;
//    @Schema(description = "Метка блокировки пользователя")
//    private boolean isBlocked;
//
//    private String emojiStatus;
//
//    private LocalDateTime deletionTimestamp;
//
//    private LocalDateTime createdDate;
//
//    private LocalDateTime lastModifiedDate;


}
