package ru.kolivim.estimates.calculator.impl.mapper.auth;


import org.springframework.security.oauth2.jwt.Jwt;
import ru.kolivim.estimates.calculator.api.dto.auth.JwtDto;

import java.util.UUID;

public class JwtMapper {
    public static JwtDto JwtDtoFromJwt(Jwt jwt){
        return JwtDto.builder()
                .id(UUID.fromString(jwt.getClaim("user_id")))
                .login(jwt.getSubject())
//                .roles(jwt.getClaim("roles"))
                .build();
    }
}
