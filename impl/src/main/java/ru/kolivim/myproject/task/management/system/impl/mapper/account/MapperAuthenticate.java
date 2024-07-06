package ru.kolivim.myproject.task.management.system.impl.mapper.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.User;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapperAuthenticate {

    @Mappings({
            @Mapping(target = "isDeleted", source = "isDeleted", defaultValue = "false")
             })
    Account toAccount(RegistrationDto registrationDto);

    UserDto toUserDTO(Account account);
}
