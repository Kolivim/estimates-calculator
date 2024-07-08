package ru.kolivim.estimates.calculator.impl.resource.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.estimates.calculator.api.dto.user.UserDto;
import ru.kolivim.estimates.calculator.api.resource.account.AccountResource;
import ru.kolivim.estimates.calculator.impl.service.account.AccountService;

import javax.security.auth.login.AccountException;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountResourceImpl implements AccountResource {

    private final AccountService accountServices;

    @Override
    public ResponseEntity<UserDto> create(UserDto userDto) throws AccountException {
        log.info("AccountResourceImpl:create(UserDto userDto) startMethod, UserDto: {}", userDto);
        return ResponseEntity.ok(accountServices.create(userDto));
    }
}
