package ru.kolivim.estimates.calculator.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeEmailDto {

    @Schema(description = "Email")
    private EmailDto email;
}
