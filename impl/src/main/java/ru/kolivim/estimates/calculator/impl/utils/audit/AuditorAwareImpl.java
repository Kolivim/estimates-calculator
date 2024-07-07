package ru.kolivim.estimates.calculator.impl.utils.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import ru.kolivim.estimates.calculator.api.dto.auth.JwtDto;
import ru.kolivim.estimates.calculator.domain.base.audit.UserJsonType;
import ru.kolivim.estimates.calculator.impl.utils.auth.AuthUtil;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<UserJsonType> {

    @Override
    public Optional<UserJsonType> getCurrentAuditor() {
        JwtDto jwtDto = AuthUtil.getJwtDto();
        if (jwtDto==null){
            return Optional.of(unauthorizedJson());
        }
        UserJsonType json = new UserJsonType(jwtDto.getId().toString(), jwtDto.getLogin());
        return Optional.of(json);
    }

    private UserJsonType unauthorizedJson(){
        return new UserJsonType();
    }
}