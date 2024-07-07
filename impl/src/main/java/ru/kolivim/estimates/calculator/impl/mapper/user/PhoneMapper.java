package ru.kolivim.estimates.calculator.impl.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.kolivim.estimates.calculator.api.dto.auth.RegistrationDto;
import ru.kolivim.estimates.calculator.domain.user.Phone;

import java.util.UUID;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PhoneMapper {

    @Mappings({
            @Mapping(target = "isDeleted", source = "registrationDto.isDeleted", defaultValue = "false"),
            @Mapping(target = "phone", source = "registrationDto.phone"),
            @Mapping(target = "userId", source = "accountId")
    })
    Phone toPhone(RegistrationDto registrationDto, UUID accountId);

}
