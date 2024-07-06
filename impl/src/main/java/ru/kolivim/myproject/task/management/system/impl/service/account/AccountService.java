package ru.kolivim.myproject.task.management.system.impl.service.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountSearchDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.AuthenticateDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.api.dto.search.BaseSearchDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.role.Role;
import ru.kolivim.myproject.task.management.system.domain.user.Email;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.domain.user.User;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAccount;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAuthenticate;
import ru.kolivim.myproject.task.management.system.impl.mapper.user.EmailMapper;
import ru.kolivim.myproject.task.management.system.impl.mapper.user.PhoneMapper;
import ru.kolivim.myproject.task.management.system.impl.repository.account.AccountRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.EmailRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.PhoneRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.UserRepository;
import ru.kolivim.myproject.task.management.system.impl.service.role.RoleService;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;

import javax.security.auth.login.AccountException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
//    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;


//    private final MapperAuthenticate mapperAuthenticate;
    private final MapperAccount mapperAccount;
    private final PhoneMapper phoneMapper;
    private final EmailMapper emailMapper;

    private static final int MAX_PERCENT = 207;
    private static final  int INCREASE_PERCENT = 5;


    public Boolean create(RegistrationDto registrationDto) {
        log.info("AccountService: create(RegistrationDto registrationDto), registrationDto: {}",
                registrationDto);

        Account account = accountRepository.save(mapperAccount.toAccount(registrationDto, getMaxBalance(registrationDto)));
        Phone phone = phoneRepository.save(phoneMapper.toPhone(registrationDto, account.getId()));
        Email email = emailRepository.save(emailMapper.toEmail(registrationDto, account.getId()));

        log.info("AccountService: create(*) endMethod, Account: {}, Phone: {}, Email: {}",
                account, phone, email);
        return true;
    }

    /** Нужно делать потокобезопасным */
    @Scheduled(cron = "${cron.addPercent}")
    public void addPercent() {
        log.info("AccountService: addPercent() startMethod");
        List<Account> accountList = accountRepository.findAll();
        log.info("AccountService: addPercent() получен List<Account> accountList: {}", accountList);
        accountList.stream()
                .filter(acc -> acc.getIsDeleted() == false)
                .filter(acc -> incrBalance(acc.getBalance()) <= acc.getMaxBalance())
                .forEach(acc -> {
                    acc.setBalance(incrBalance(acc.getBalance()));
                    log.info("AccountService: addPercent() сохранен Account: {}", accountRepository.save(acc));});
        accountRepository.saveAll(accountList);
    }

    public synchronized boolean pay(UUID userToPay, UUID user, Double sum) {
        log.info("AccountService: pay(UUID userToPay, UUID user) startMethod, UUID userToPay : {}, UUID user: {}" ,
                userToPay, user);

        Account account = accountRepository.findById(user).orElseThrow();
        Account accountTo = accountRepository.findById(userToPay).orElseThrow();

        Double sumAfter = account.getBalance() - sum;
        Double sumAfterTo = accountTo.getBalance() + sum;

        if(sumAfter > 0 && !userToPay.equals(user)) {
            account.setBalance(sumAfter);
            accountTo.setBalance(sumAfterTo);
            accountRepository.save(account);
            accountRepository.save(accountTo);
        } else {
            return false;
        }

        return false;
    }



    private double getMaxBalance(RegistrationDto registrationDto) {
        log.info("AccountService: getMaxBalance(RegistrationDto registrationDto), registrationDto: {}",
                registrationDto);

        double maxBalance = registrationDto.getBalance() * MAX_PERCENT / 100;

        log.info("AccountService: getMaxBalance(*) endMethod, mxBalance: {}", maxBalance);
        return maxBalance;

    }

    private double incrBalance(Double balance) {
        log.info("AccountService: incrBalance(Double balance), Double: {}", balance);
        balance = balance + (balance * INCREASE_PERCENT / 100);
        log.info("AccountService: incrBalance(*), in out balance: {}", balance);
        return balance;

    }

    private BaseSearchDto getBaseSearchDto(){
        BaseSearchDto baseSearchDto = new BaseSearchDto();
        baseSearchDto.setIsDeleted(false);
        return  baseSearchDto;
    }
}



