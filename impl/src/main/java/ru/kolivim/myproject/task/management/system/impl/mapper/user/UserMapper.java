package ru.kolivim.myproject.task.management.system.impl.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.domain.user.User;

import java.util.ArrayList;
import java.util.UUID;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "isDeleted", source = "user.isDeleted"),
            @Mapping(target = "phoneList", source = "phoneList"),
            @Mapping(target = "emailList", source = "emailList"),
            @Mapping(target = "birthDate", source = "user.birthDate"),
            @Mapping(target = "fullname", source = "user.fullname"),
            @Mapping(target = "login", source = "user.login"),
            @Mapping(target = "password", source = "user.password")
    })
    UserDto toUserDTO(User user, ArrayList<String> phoneList, ArrayList<String> emailList);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "isDeleted", source = "isDeleted"),
            @Mapping(target = "phoneList", ignore = true),
            @Mapping(target = "emailList", ignore = true),
            @Mapping(target = "birthDate", source = "birthDate"),
            @Mapping(target = "fullname", source = "fullname"),
            @Mapping(target = "login", source = "login"),
            @Mapping(target = "password", source = "password")
    })
    UserDto toUserDTO(User user);
}
