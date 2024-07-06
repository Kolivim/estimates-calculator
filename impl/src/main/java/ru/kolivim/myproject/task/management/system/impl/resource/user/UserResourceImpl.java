package ru.kolivim.myproject.task.management.system.impl.resource.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDataDTO;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.api.resource.user.UserResource;
import ru.kolivim.myproject.task.management.system.impl.service.user.UserService;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;

import javax.security.auth.login.AccountException;
import java.util.UUID;

/**
 * Account
 *
 * @taras281 Taras
 */
@Slf4j
@RestController
//@RequestMapping("api/v1/user")
//@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

    private final UserService userService;

    @Override
    public ResponseEntity addPhone(@RequestBody UserDataDTO userDataDTO) {
        userService.addPhone(userDataDTO);
        return ResponseEntity.ok("Номер добавлен");
    }

    @Override
    public ResponseEntity  updatePhone(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.updatePhone(userDataDTO));
    }

    @Override
    public ResponseEntity deletePhone(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.deletePhone(userDataDTO));
    }

    @Override
    public ResponseEntity addEmail(@RequestBody UserDataDTO userDataDTO) {
        userService.addEmail(userDataDTO);
        return ResponseEntity.ok("Email добавлен");
    }

    @Override
    public ResponseEntity  updateEmail(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.updateEmail(userDataDTO));
    }

    @Override
    public ResponseEntity deleteEmail(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.deleteEmail(userDataDTO));
    }

    @Override
    public ResponseEntity<Page<UserDto>> searchFullname(@RequestBody UserDto userDto, Pageable pageable) {
        return ResponseEntity.ok(userService.searchFullname(userDto, pageable));
    }

    @Override
    public ResponseEntity<Page<UserDto>> searchBirthDay(@RequestBody UserDto userDto, Pageable pageable) {
        return ResponseEntity.ok(userService.searchBirthDay(userDto, pageable));
    }

    @Override
    public ResponseEntity<UserDto> searchPhone(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.searchPhone(userDataDTO));
    }

    @Override
    public ResponseEntity<UserDto> searchEmail(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.searchEmail(userDataDTO));
    }

    @Override
    public ResponseEntity<UserDto> pay(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.pay(userDataDTO));
    }



    //////////////////////////////////////////////////////////////////////////////////

    @Override
    @GetMapping() /** Поубирать везде */
    public ResponseEntity get(@RequestParam String email) {
        log.info("AccountResourceImpl:get() startMethod");
        try {
            return ResponseEntity.ok(userService.getByEmail(email));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    @PutMapping()
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto account) {
        log.info("AccountResourceImpl:update() startMethod");
        try {
            return ResponseEntity.ok(userService.update(account));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    @PostMapping()
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto account) {
        log.info("AccountResourceImpl:create() startMethod");
        try {
            return ResponseEntity.ok(userService.create(account));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity getMe() {
        log.info("AccountResourceImpl:getMe() startMethod");
        try {
            return ResponseEntity.ok(userService.getMe());
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    public ResponseEntity putMe(@RequestBody AccountDto accountDto) throws AccountException {
        log.info("AccountResourceImpl:putMe() startMethod");
        try {
            return ResponseEntity.ok(userService.putMe(accountDto));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }
    @Override
    public ResponseEntity deleteMe() throws AccountException {
        log.info("AccountResourceImpl:deleteMe() startMethod");
        try {
            return ResponseEntity.ok(userService.delete());
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    public ResponseEntity getId(@PathVariable UUID id) {
        log.info("AccountResourceImpl:getId() startMethod");
        try {
            return ResponseEntity.ok(userService.getId(id));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    public ResponseEntity deleteId(UUID id) throws AccountException {
        log.info("AccountResourceImpl:deleteId() startMethod");
        try {
            return ResponseEntity.ok(userService.deleteId(id));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    private ResponseEntity generatorResponse(AccountException e) {
        log.info("AccountResourceImpl:generateResponse() startMethod");
        if(e.getMessage().equals("unautorized")){
            return ResponseEntity.status(401).body("Unauthorized");}
        return ResponseEntity.status(400).body("Bad request");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.info("AccountResourceImpl:test() startMethod");
        JwtDto jwtDto = AuthUtil.getJwtDto();
        UUID userId = AuthUtil.getUserId();
        System.out.println(jwtDto);
        System.out.println(userId);
        return ResponseEntity.ok("hello from test method");
    }
}
