package ru.kolivim.myproject.task.management.system.impl.service.passRecovery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolivim.myproject.task.management.system.api.dto.auth.NewPasswordDto;

import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.passwordRecovery.RecoveryToken;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAccount;
import ru.kolivim.myproject.task.management.system.impl.repository.recoveryToken.RecoveryTokenRepository;
import ru.kolivim.myproject.task.management.system.impl.service.user.UserService;
import ru.kolivim.myproject.task.management.system.impl.utils.mail.MailUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecoveryService {
    private final UserService userService;
    private final RecoveryTokenRepository tokenRepository;
    private final MailUtil mailUtil;
    private final MapperAccount accountMapper;
    private final String host = "localhost:8099";

    public Boolean recover(String email) {
        ru.kolivim.myproject.task.management.system.domain.account.Account account = userService.getAccountByEmail(email);
        if (account == null) {
            return false;
        }
        RecoveryToken token = new RecoveryToken(account, LocalDateTime.now().plus(10, ChronoUnit.MINUTES));
        tokenRepository.save(token);
        mailUtil.send(email, getMessage(token.getId()));
        return true;
    }

    private String getMessage(UUID tokenId) {
        return "для восстановления пароля перейдите по ссылке:\n" +
                "http://" + host + "/change-password/" + tokenId.toString();
    }

    public boolean setNewPassword(String linkId, NewPasswordDto passwordDto) {
        Optional<RecoveryToken> optionalToken = tokenRepository.findById(UUID.fromString(linkId));
        if (optionalToken.isEmpty()) {
            return false;
        }
        RecoveryToken token = optionalToken.get();
        if (token.getExpirationTime().isBefore(LocalDateTime.now())) {
            return false;
        }
        Account account = token.getAccount();
        account.setPassword(passwordDto.getPassword());
        userService.save(account);
        tokenRepository.delete(token);
        return true;
    }
}
