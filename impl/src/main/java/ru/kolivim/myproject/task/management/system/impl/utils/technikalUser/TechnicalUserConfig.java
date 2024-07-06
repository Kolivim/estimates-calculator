package ru.kolivim.myproject.task.management.system.impl.utils.technikalUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class TechnicalUserConfig {
    private final JwtEncoder accessTokenEncoder;

    public <T> T executeByTechnicalUser(Supplier<T> lambda) {
        log.info("TechnicalUserConfig:executeByTechnicalUser start method");
        try {
            createContextForKafka();
            return lambda.get();
        } finally {
            System.out.println(SecurityContextHolder.createEmptyContext());
        }
    }

    public <T> T executeByTestUser(Supplier<T> lambda) {
        log.info("TechnicalUserConfig:executeByTechnicalUser start method");
        try {
            createTestContext();
            return lambda.get();
        } finally {
            System.out.println(SecurityContextHolder.createEmptyContext());
        }
    }

    private void createContextForKafka() {
        log.info("TechnicalUserConfig:createContextForKafka start method");
        JwtDto jwtDto = new JwtDto();
        jwtDto.setUserId(UUID.randomUUID().toString());
        jwtDto.setLogin("kafka@email");
//        jwtDto.setRoles(Collections.singletonList("ROLES_KAFKA"));
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(createToken(jwtDto));
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(jwtAuthenticationToken);
    }

    private void createTestContext() {
        log.info("TechnicalUserConfig:createTestContext start method");
        JwtDto jwtDto = new JwtDto();
        jwtDto.setUserId("f7d1002-9cc6-11ee-8c90-0242ac120002");
        jwtDto.setLogin("test@email");
//        jwtDto.setRoles(Collections.singletonList("ROLES_KAFKA"));
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(createToken(jwtDto));
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(jwtAuthenticationToken);
    }

    public Jwt createToken(JwtDto jwtDto) {
        log.info("TechnicalUserConfig:createToken start method");
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("transactions")
                .issuedAt(now)
                .expiresAt(now.plus(8, ChronoUnit.HOURS))
                .subject(jwtDto.getLogin())
//                .claim("roles", jwtDto.getRoles())
                .claim("user_id", jwtDto.getUserId())
                .build();
        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet));
    }

}
