package ru.kolivim.estimates.calculator.impl.service.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.kolivim.estimates.calculator.api.dto.account.AccountDto;
import ru.kolivim.estimates.calculator.api.dto.user.UserDto;
import ru.kolivim.estimates.calculator.domain.account.Account;
import ru.kolivim.estimates.calculator.impl.mapper.account.AccountMapper;
import ru.kolivim.estimates.calculator.impl.repository.account.AccountRepository;

import javax.security.auth.login.AccountException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
//    private static final String BADREUQEST = "bad reqest";

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

//    private final RoleService roleService;

    public UserDto create(UserDto userDto) throws AccountException {
        log.info("AccountService:create(UserDto userDto) startMethod, получена UserDto:/n{}", userDto);


        /**
         TODO:
         Добавить сюда проверку на существование с выбросом эксепшена для остановки программы !!!
         Через вызов отдельного метода в этом месте !!!
         */


        /**
         lastOnlineTime оставляем не заполненным - принимаем что пользователь не может создать сам себя,
         это прерогатива администратора TODO: Рассмотреть возможность создания VIEWER'a самим собой и поправить тогда логику
         */

//        accountDto.setDepartment("тестовый_СделатьМетод"); // TODO: Достаем по position её department и кладем его сюда, через вызов отдельного метода
//        accountDto.setPersonnelNumber("тестовый_СделатьМетод"); // TODO: Достаем по position её department и ищем последний актуальный номер и кладем сюда следующий за ним, через вызов отдельного метода
//        accountDto.setRegistrationDate(ZonedDateTime.now());
//        accountDto.setLastModifiedDate(ZonedDateTime.now());

        /**
         В тестовом режиме можно временно проставить вручную всем создаваемым пользователям Estimate
         для дальнейшего формирования основной логики
         Через вызов отдельного метода в этом месте !!!
         TODO: Добавить назначение роли пользователю и соответсвующее поле в ДТО / другую ДТОшку
         */

        save(new Account());
        return new UserDto();
    }

    public AccountDto create(AccountDto accountDto) throws AccountException {
        log.info("AccountService:create(AccountDto accountDto) startMethod, получена AccountDto:/n{}", accountDto);


        /**
         TODO:
         Добавить сюда проверку на существование с выбросом эксепшена для остановки программы !!!
         Через вызов отдельного метода в этом месте !!!
         */


        /**
         lastOnlineTime оставляем не заполненным - принимаем что пользователь не может создать сам себя,
         это прерогатива администратора TODO: Рассмотреть возможность создания VIEWER'a самим собой и поправить тогда логику
         */
        accountDto.setDepartment("тестовый_СделатьМетод"); // TODO: Достаем по position её department и кладем его сюда, через вызов отдельного метода
        accountDto.setPersonnelNumber("тестовый_СделатьМетод"); // TODO: Достаем по position её department и ищем последний актуальный номер и кладем сюда следующий за ним, через вызов отдельного метода
        accountDto.setRegistrationDate(ZonedDateTime.now());
        accountDto.setLastModifiedDate(ZonedDateTime.now());

        /**
         В тестовом режиме можно временно проставить вручную всем создаваемым пользователям Estimate
         для дальнейшего формирования основной логики
         Через вызов отдельного метода в этом месте !!!
         TODO: Добавить назначение роли пользователю и соответсвующее поле в ДТО / другую ДТОшку
         */

        return accountMapper.toDto(save(accountMapper.toAccount(accountDto)));
    }

    private Account save(Account account) {
        log.info("AccountService:save(Account account) startMethod, получен к сохранению Account:/n{}", account);
        return accountRepository.save(account);
    }

    /**
     private ZonedDateTime lastOnlineTime;
     private String about;
     private String phone;
     private String email;
     private String position;
     private String department;
     private String personnelNumber;
     private ZonedDateTime registrationDate;
     private ZonedDateTime lastModifiedDate;
     */


//    public AccountDto update(AccountDto accountDto) throws AccountException {
//        log.info("AccountService:putMe() startMethod");
//        Account account = accountDto.getId() != null ? accountRepository
//                .findById(accountDto.getId()).get() : accountRepository.findById(AuthUtil.getUserId()).get();
//        account = mapperAccount.rewriteEntity(accountRepository.findById(account.getId()).get(), accountDto);
//        accountRepository.save(account);
//        return mapperAccount.toDto(account);
//    }

//    public AccountDto getByEmail(String email) throws AccountException {
//        log.info("AccountService:get(String email) startMethod");
//        return mapperAccount.toDto(accountRepository.findFirstByEmail(email).orElseThrow(() -> new AccountException("BADREUQEST")));
//    }

//    public AccountDto getId(UUID uuid) throws AccountException {
//        log.info("AccountService:get(String email) startMethod");
//        return mapperAccount.toDto(accountRepository.findById(uuid).orElseThrow(() -> new AccountException("BADREUQEST")));
//    }

//    public AccountDto getMe() throws AccountException {
//        log.info("AccountService: getMe() startMethod");
//        return mapperAccount.toDto(accountRepository.findById(AuthUtil.getUserId()).orElseThrow(() -> new AccountException(BADREUQEST)));
//    }

//    public Page<AccountDto> getAll(AccountSearchDto accountSearchDto, Pageable pageable) throws AccountException {
//        Specification spec = SpecificationUtils.equal(Account_.COUNTRY, accountSearchDto.getCountry())
//                .or(SpecificationUtils.like(Account_.FIRST_NAME, accountSearchDto.getFirstName()))
//                .or(SpecificationUtils.like(Account_.LAST_NAME, accountSearchDto.getLastName()))
//                .or(SpecificationUtils.like(Account_.CITY, accountSearchDto.getCity()))
//                .or(SpecificationUtils.equal(Account_.EMAIL, accountSearchDto.getEmail()))
//                .or(SpecificationUtils.between(Account_.BIRTH_DATE, accountSearchDto.getAgeFrom(), accountSearchDto.getAgeTo()))
//                .or(SpecificationUtils.in(Account_.ID, accountSearchDto.getIds()));
//        Page<Account> accounts = accountRepository.findAll(spec, pageable);
//        return accounts.map(mapperAccount::toDto);
//    }

//    public AccountDto putMe(AccountDto accountDto) throws AccountException {
//        log.info("AccountService:putMe() startMethod");
//        return mapperAccount.toDto(mapperAccount.rewriteEntity(accountRepository.findById(AuthUtil.getUserId()).get(), accountDto));
//    }

//    public boolean delete() throws AccountException {
//        log.info("AccountService:delete() startMethod");
//        accountRepository.deleteById(AuthUtil.getUserId());
//        return true;
//    }

//    public boolean deleteId(UUID id) throws AccountException {
//        log.info("AccountService:deleteId() startMethod");
//        accountRepository.deleteById(id);
//        return true;
//    }


//    public JwtDto getJwtDto(AuthenticateDto authenticateDto) {
//        log.info("AccountService:getJwtDto() startMethod");
//        Optional<Account> account = accountRepository.findFirstByEmail(authenticateDto.getEmail());
//        Assert.isTrue(account.isPresent());
//        Assert.isTrue(account.get().getPassword().equals(authenticateDto.getPassword()));
//        JwtDto jwtDto = new JwtDto();
//        jwtDto.setId(account.get().getId());
//        jwtDto.setEmail(account.get().getEmail());
//        jwtDto.setRoles(listOfRolesFromSetOfRoles(account.get().getRoles()));
//        account.get().setLastOnlineTime(LocalDateTime.now());
//        return jwtDto;
//    }

//    public Boolean doesAccountWithSuchEmailExist(String email) {
//        return accountRepository.findFirstByEmail(email).isPresent();
//    }

//    private List<String> listOfRolesFromSetOfRoles(Set<Role> roles) {
//        log.info("AccountService:listOfRolesFromSetOfRoles() startMethod");
//        ArrayList<String> roleNames = new ArrayList<>();
//        for (Role role : roles) {
//            roleNames.add(role.getRole());
//        }
//        return roleNames;
//    }

//    public Account getAccountByEmail(String email) {
//        return accountRepository.findFirstByEmail(email).orElse(null);
//    }


}
