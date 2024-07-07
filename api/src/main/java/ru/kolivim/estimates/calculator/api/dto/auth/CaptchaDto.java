package ru.kolivim.estimates.calculator.api.dto.auth;

import lombok.Data;

@Data
public class CaptchaDto {
    private String secret;
    private String image;
}
