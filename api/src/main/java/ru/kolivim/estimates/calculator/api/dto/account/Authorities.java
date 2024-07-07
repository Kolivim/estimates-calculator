package ru.kolivim.estimates.calculator.api.dto.account;

import lombok.Data;

import java.util.UUID;

@Data
public class Authorities {

    private UUID id;
    private String authority;
}
