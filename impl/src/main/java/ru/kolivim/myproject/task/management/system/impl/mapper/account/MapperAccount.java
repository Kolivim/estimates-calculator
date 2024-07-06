package ru.kolivim.myproject.task.management.system.impl.mapper.account;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;


@Slf4j
@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class MapperAccount {

    public Account toAccount(RegistrationDto registrationDto, Double maxBalance) {
        log.info("MapperAccount:toAccount(RegistrationDto registrationDto, Float maxBalance) startMethod, " +
                "RegistrationDto: {}, maxBalance: {}", registrationDto, maxBalance);
        Account account = toAccount(registrationDto);
        account.setMaxBalance(maxBalance);
        log.info("MapperAccount:toAccount(*) endMethod, Account: {}", account);
        return account;
    }

    public Account toAccount(RegistrationDto registrationDto) {
        log.info("MapperAccount:toAccount(RegistrationDto registrationDto) startMethod, RegistrationDto: {}",
                registrationDto);
        Account account = new Account();
        account.setIsDeleted(false);
        account.setBalance(registrationDto.getBalance());
        account.setBirthDate(registrationDto.getBirthDate());
        account.setFullname(registrationDto.getFullName());
        account.setLogin(registrationDto.getLogin());
        account.setPassword(registrationDto.getPassword());
//        ArrayList<String> phoneList = new ArrayList<>();
//        phoneList.add(registrationDto.getPhone());
//        account.setPhoneList(phoneList);
//        ArrayList<String> emailList = new ArrayList<>();
//        emailList.add(registrationDto.getEmail());
//        account.setEmailList(emailList);
        log.info("MapperAccount:toAccount(RegistrationDto registrationDto) endMethod, Account: {}", account);
        return account;
    }

/*
    public abstract AccountDto toDto(Account account);
    public abstract Account toEntity(AccountDto accountDto);
    public abstract Account rewriteEntity(@MappingTarget Account account, AccountDto accountDto);
*/

    /*
    @Mapping(target = "password", source = "dto.password1")
    public abstract AccountDto accountDtoFromRegistrationDto(RegistrationDto dto);
    */
}
