package ru.kolivim.myproject.task.management.system.api.dto.auth;

import lombok.Data;

@Data
public class CaptchaDto {
    private String secret;
    private String image;
}
