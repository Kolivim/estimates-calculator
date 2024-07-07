package ru.kolivim.estimates.calculator.impl.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.kolivim.estimates.calculator.api.dto.auth.RegistrationDto;
import ru.kolivim.estimates.calculator.domain.user.Email;

import java.util.UUID;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmailMapper {

    @Mappings({
            @Mapping(target = "isDeleted", source = "registrationDto.isDeleted", defaultValue = "false"),
            @Mapping(target = "email", source = "registrationDto.email"),
            @Mapping(target = "userId", source = "accountId")
    })
    Email toEmail(RegistrationDto registrationDto, UUID accountId);

}
