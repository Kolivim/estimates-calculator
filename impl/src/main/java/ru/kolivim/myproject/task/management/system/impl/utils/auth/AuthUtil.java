package ru.kolivim.myproject.task.management.system.impl.utils.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.impl.mapper.auth.JwtMapper;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthUtil {
    public static JwtDto getJwtDto(){
        Jwt jwt;
        try {
            jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        }catch (ClassCastException e){
            return null;
        }
        return JwtMapper.JwtDtoFromJwt(jwt);
    }
    public static UUID getUserId(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID uuid = UUID.fromString(jwt.getClaim("user_id"));
        return UUID.fromString(jwt.getClaim("user_id"));
    }

}
