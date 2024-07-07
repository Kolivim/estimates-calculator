package ru.kolivim.estimates.calculator.impl.service.passRecovery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolivim.estimates.calculator.impl.mapper.account.MapperAccount;
import ru.kolivim.estimates.calculator.impl.service.user.UserService;
import ru.kolivim.estimates.calculator.impl.utils.mail.MailUtil;
import ru.kolivim.estimates.calculator.api.dto.auth.NewPasswordDto;

import ru.kolivim.estimates.calculator.domain.account.Account;
import ru.kolivim.estimates.calculator.domain.passwordRecovery.RecoveryToken;
import ru.kolivim.estimates.calculator.impl.repository.recoveryToken.RecoveryTokenRepository;

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
        Account account = userService.getAccountByEmail(email);
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
