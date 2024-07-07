package ru.kolivim.estimates.calculator.impl.mapper.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.kolivim.estimates.calculator.api.dto.auth.RegistrationDto;
import ru.kolivim.estimates.calculator.api.dto.user.UserDto;
import ru.kolivim.estimates.calculator.domain.account.Account;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapperAuthenticate {

    @Mappings({
            @Mapping(target = "isDeleted", source = "isDeleted", defaultValue = "false")
             })
    Account toAccount(RegistrationDto registrationDto);

    UserDto toUserDTO(Account account);
}
