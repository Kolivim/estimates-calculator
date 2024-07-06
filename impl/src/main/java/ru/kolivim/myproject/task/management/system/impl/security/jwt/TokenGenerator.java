package ru.kolivim.myproject.task.management.system.impl.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private final JwtEncoder accessTokenEncoder;

    public String createToken(JwtDto jwtDto) {
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("transactions")     //                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(8, ChronoUnit.HOURS))   // .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .subject(jwtDto.getLogin())
//                .claim("roles", jwtDto.getRoles())
                .claim("user_id", jwtDto.getId())
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
