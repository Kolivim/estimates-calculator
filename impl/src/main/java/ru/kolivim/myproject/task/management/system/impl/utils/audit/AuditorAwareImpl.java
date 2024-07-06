package ru.kolivim.myproject.task.management.system.impl.utils.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.domain.base.audit.UserJsonType;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;

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