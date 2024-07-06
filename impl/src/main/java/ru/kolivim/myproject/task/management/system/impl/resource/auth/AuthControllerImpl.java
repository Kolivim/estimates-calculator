package ru.kolivim.myproject.task.management.system.impl.resource.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kolivim.myproject.task.management.system.api.dto.auth.*;
import ru.kolivim.myproject.task.management.system.api.resource.auth.AuthController;
import ru.kolivim.myproject.task.management.system.impl.service.passRecovery.RecoveryService;
import ru.kolivim.myproject.task.management.system.impl.service.auth.AuthService;
import ru.kolivim.myproject.task.management.system.impl.service.auth.CaptchaService;

@Slf4j
@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final CaptchaService captchaService;
    private final RecoveryService recoveryService;
    private final AuthService authService;


    @Override
    public ResponseEntity<AuthenticateResponseDto> login(AuthenticateDto authenticateDto) {
        log.info("AuthControllerImpl: login(RegistrationDto registrationDto), registrationDto: {}",
                authenticateDto);

        AuthenticateResponseDto authenticateResponseDto = authService.login(authenticateDto);
        if(authenticateResponseDto.getAccessToken()==null){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(authenticateResponseDto);
    }

    @Override
    public ResponseEntity<String> register(RegistrationDto registrationDto) {
        log.info("AuthControllerImpl: register(RegistrationDto registrationDto), registrationDto: {}",
                registrationDto);

        /** Отключил проверку капчи */
        /*
        if(!captchaService.captchaIsCorrect(registrationDto.getCaptchaCode(),registrationDto.getCaptchaSecret())){
            return ResponseEntity.badRequest().body("wrong captcha code");
        }
        */

        if(!authService.register(registrationDto)){
            return ResponseEntity.badRequest().body("base has some user data");
        }

        log.info("AuthControllerImpl: register(RegistrationDto registrationDto) endMethod : registered");
        return ResponseEntity.ok("registered");
    }

    @Override
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("logged out");
    }

    /** Ниже исходники */

    @Override
    public ResponseEntity<String> sendRecoveryEmail(PasswordRecoveryDto dto) {
        if(!recoveryService.recover(dto.getEmail())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> changePassword(String linkId, NewPasswordDto passwordDto) {
        if(recoveryService.setNewPassword(linkId, passwordDto)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<CaptchaDto> getCaptcha() {
        return ResponseEntity.ok(captchaService.getCaptcha());
    }

}
