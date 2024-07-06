package ru.kolivim.myproject.task.management.system.impl.service.user;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountSearchDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.AuthenticateDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDataDTO;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.role.Role;
import ru.kolivim.myproject.task.management.system.domain.user.Email;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.domain.user.User;
import ru.kolivim.myproject.task.management.system.domain.user.User_;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAccount;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAuthenticate;
import ru.kolivim.myproject.task.management.system.impl.mapper.user.UserMapper;
import ru.kolivim.myproject.task.management.system.impl.repository.user.EmailRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.PhoneRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.UserRepository;
import ru.kolivim.myproject.task.management.system.impl.service.account.AccountService;
import ru.kolivim.myproject.task.management.system.impl.service.role.RoleService;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;
import ru.kolivim.myproject.task.management.system.impl.repository.account.AccountRepository;
import ru.kolivim.myproject.task.management.system.api.dto.search.BaseSearchDto;
import ru.kolivim.myproject.task.management.system.impl.utils.specification.SpecificationUtils;

import javax.security.auth.login.AccountException;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final AccountService accountService;

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;
    private final MapperAuthenticate mapperAuthenticate;
    private final MapperAccount mapperAccount;
    private final UserMapper userMapper;

    /** Отрефакторить: */
    private static final String BADREUQEST = "bad reqest";
    private final AccountRepository accountRepository;
    private final RoleService roleService;

    public Boolean doesUserDataExist(RegistrationDto registrationDto) {
        log.info("UserService: doesUserDataExist(RegistrationDto registrationDto) startMethod, RegistrationDto: {}",
                registrationDto);

        Optional<User> user = userRepository.findByLoginAndIsDeletedFalse(registrationDto.getLogin());
        int countPhone = phoneRepository.countByPhone(registrationDto.getPhone());
        int countEmail = emailRepository.countByEmail(registrationDto.getEmail());

        log.info("UserService: doesUserDataExist(*) received countPhone: {}, countEmail: {}, " +
                        "Optional<User> :{}", countPhone, countEmail, user);

        return !user.isEmpty() || countPhone !=0 || countEmail != 0;

    }

    public JwtDto getJwtDto(AuthenticateDto authenticateDto) {
        log.info("UserService:getJwtDto(AuthenticateDto authenticateDto) startMethod, AuthenticateDto: {}",
                authenticateDto);

        Optional<User> user = userRepository.findByLogin(authenticateDto.getLogin());
        Assert.isTrue(user.isPresent());
        Assert.isTrue(user.get().getPassword().equals(authenticateDto.getPassword()));
        JwtDto jwtDto = new JwtDto();
        jwtDto.setId(user.get().getId());
        jwtDto.setLogin(user.get().getLogin());

        return jwtDto;
    }


    public UserDto create(RegistrationDto registrationDto) throws AccountException {
        log.info("UserService:create(RegistrationDto registrationDto) startMethod, RegistrationDto: {}",
                registrationDto);

        Account account = accountRepository.save(mapperAccount.toAccount(registrationDto));
        return mapperAuthenticate.toUserDTO(account);
    }

    public Boolean addPhone(UserDataDTO userDataDTO) {
        log.info("UserService: addPhone(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);
        if (phoneRepository.countByPhone(userDataDTO.getPhone()) == 0) {
            phoneRepository.save(new Phone(userDataDTO.getPhone(), AuthUtil.getUserId()));
        };
        return true;
    }

    public String updatePhone(UserDataDTO userDataDTO) {
        log.info("UserService: updatePhone(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);
        String response = "";

        Optional<Phone> optionalPhone = phoneRepository.findByPhone(userDataDTO.getPhone());
        if(optionalPhone.isPresent() && phoneRepository.countByPhone(userDataDTO.getNewPhone()) == 0) {
            Phone phone = optionalPhone.get();
            if(phone.getUserId().equals(AuthUtil.getUserId())) {
               phone.setPhone(userDataDTO.getNewPhone());
                phoneRepository.save(phone);
                response = "Номер телефона заменен";
            } else {
                response = "Заменяемый номер телефона к вам не относится";
            }
        } else {
            response = "Заменяемый номер телефона не найден / Новый номер занят";
        }

        return response;
    }

    public String deletePhone(UserDataDTO userDataDTO) {
        log.info("UserService: deletePhone(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);
        String response = "";

        Optional<Phone> optionalPhone = phoneRepository.findByPhone(userDataDTO.getPhone());
        List<Phone> phoneList = phoneRepository.findAllByUserId(AuthUtil.getUserId());
        if(optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            if(phone.getUserId().equals(AuthUtil.getUserId()) && phoneList.size()>1) {
                phoneRepository.delete(phone);
                response = "Номер телефона удален";
                log.info("UserService: deletePhone(*) delete number: {}", userDataDTO);
            } else {
                response = "Удаляемый номер телефона к вам не относится / Остался последний номер телефона";
                log.info("UserService: deletePhone(*) number data is fail: {}", userDataDTO);
            }
        } else {
            response = "Номер телефона не найден";
            log.info("UserService: deletePhone(*) number is exist: {}", userDataDTO);
        }

        return response;
    }

    public Boolean addEmail(UserDataDTO userDataDTO) {
        log.info("UserService: addEmail(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);
        if (emailRepository.countByEmail(userDataDTO.getEmail()) == 0) {
            emailRepository.save(new Email(userDataDTO.getEmail(), AuthUtil.getUserId()));
        };
        return true;
    }

    public String updateEmail(UserDataDTO userDataDTO) {
        log.info("UserService: updateEmail(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);
        String response = "";

        Optional<Email> optionalEmail = emailRepository.findByEmail(userDataDTO.getEmail());
        if(optionalEmail.isPresent() && emailRepository.countByEmail(userDataDTO.getNewEmail()) == 0) {
            Email email = optionalEmail.get();
            if(email.getUserId().equals(AuthUtil.getUserId())) {
                email.setEmail(userDataDTO.getNewEmail());
                emailRepository.save(email);
                response = "Email заменен";
            } else {
                response = "Заменяемый Email к вам не относится";
            }
        } else {
            response = "Заменяемый Email не найден / Новый Email занят";
        }

        return response;
    }

    public String deleteEmail(UserDataDTO userDataDTO) {
        log.info("UserService: deleteEmail(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);
        String response = "";

        Optional<Email> optionalEmail = emailRepository.findByEmail(userDataDTO.getEmail());
        List<Email> emailList = emailRepository.findAllByUserId(AuthUtil.getUserId());
        if(optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            if(email.getUserId().equals(AuthUtil.getUserId()) && emailList.size()>1) {
                emailRepository.delete(email);
                response = "Email удален";
                log.info("UserService: deletePhone(*) delete Email: {}", userDataDTO);
            } else {
                response = "Удаляемый номер Email / Остался последний Email";
                log.info("UserService: deletePhone(*) number data is fail: {}", userDataDTO);
            }
        } else {
            response = "Email не найден";
            log.info("UserService: deleteEmail(*) Email is exist: {}", userDataDTO);
        }

        return response;
    }

    public Page<UserDto> searchFullname(UserDto userDto, Pageable pageable) {
        log.info("UserService:searchFullname startMethod, UserDto: {}", userDto);

        Page<User> userPage = userRepository.findAllByFullnameLikeAndIsDeletedFalse(userDto.getFullname(), pageable);

        log.info("UserService:searchFullName Page<User>: {}", userPage);
        Page<UserDto> userDtoPage = userPage.map(userMapper::toUserDTO);
        log.info("UserService:searchFullName Page<UserDto>: {}", userDtoPage);

        setUserEmailsAndPhones(userDtoPage);
        log.info("UserService:searchFullName Page<UserDto> with emailList: {}", userDtoPage);
        return userDtoPage;
    }

    public Page<UserDto> searchBirthDay(UserDto userDto, Pageable pageable) {
        log.info("UserService:searchBirthDay(UserDto, Pageable) startMethod, UserDto: {}", userDto);

        Page<User> userPage = userRepository.findAllByBirthDateAfterAndIsDeletedFalse(userDto.getBirthDate(), pageable);

        log.info("UserService:searchBirthDay Page<User>: {}", userPage);
        Page<UserDto> userDtoPage = userPage.map(userMapper::toUserDTO);
        log.info("UserService:searchBirthDay Page<UserDto>: {}", userDtoPage);


        setUserEmailsAndPhones(userDtoPage);
        log.info("UserService:searchBirthDay Page<UserDto> with emailList: {}", userDtoPage);
        return userDtoPage;
    }

    public UserDto searchPhone(UserDataDTO userDataDTO) {
        log.info("UserService:searchPhone(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);

        Phone phone = phoneRepository.findByPhone(userDataDTO.getPhone()).orElseThrow();
        User user = userRepository.findById(phone.getUserId()).orElseThrow();
        log.info("UserService:searchPhone User: {}", user);

        UserDto userDto = userMapper.toUserDTO(user);
        setUserEmailsAndPhones(userDto);
        log.info("UserService:searchPhone(*) UserDto with EandP: {}", userDto);
        return userDto;
    }

    public UserDto searchEmail(UserDataDTO userDataDTO) {
        log.info("UserService:searchEmail(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);

        Email email = emailRepository.findByEmail(userDataDTO.getEmail()).orElseThrow();
        User user = userRepository.findById(email.getUserId()).orElseThrow();
        log.info("UserService:searchEmail(*) User: {}", user);

        return getUserDto(user);
    }

    public UserDto pay(UserDataDTO userDataDTO) {
        log.info("UserService:pay(UserDataDTO userDataDTO) startMethod, UserDataDTO: {}", userDataDTO);

        UUID userToPay = getUserToPay(userDataDTO).getId();
        UUID user = AuthUtil.getUserId();

        accountService.pay(userToPay, user, userDataDTO.getSum());

        User userAfterPay = userRepository.findByIdAndIsDeletedFalse(user).orElseThrow();
        return getUserDto(userAfterPay);
    }

    private User getUserToPay(UserDataDTO userDataDTO) {
        log.info("UserService:getUserToPay(UserDataDTO userDataDTO)  startMethod, UserDataDTO: {}", userDataDTO);
        User user = new User();

        if (!userDataDTO.getLogin().isEmpty()) {
            user = userRepository.findByLoginAndIsDeletedFalse(userDataDTO.getLogin()).orElseThrow();
            log.info("UserService:getUserToPay(*) with Login and received User: {}", user);
            return user;
        }

        if (!userDataDTO.getPhone().isEmpty()) {
            Phone phone = phoneRepository.findByPhone(userDataDTO.getPhone()).orElseThrow();
            user = userRepository.findByIdAndIsDeletedFalse(phone.getUserId()).orElseThrow();
            log.info("UserService:getUserToPay(*) with Phone and received User: {}", user);
            return user;
        }

        if (!userDataDTO.getEmail().isEmpty()) {
            Email email = emailRepository.findByEmail(userDataDTO.getEmail()).orElseThrow();
            user = userRepository.findByIdAndIsDeletedFalse(email.getUserId()).orElseThrow();
            log.info("UserService:getUserToPay(*) with Email and received User: {}", user);
            return user;
        }

        return user;
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = userMapper.toUserDTO(user);
        setUserEmailsAndPhones(userDto);
        log.info("UserService:searchEmail(*) UserDto with EandP: {}", userDto);
        return userDto;
    }

    private void setUserEmailsAndPhones(Page<UserDto> userDtoPage) {
        log.info("UserService:setUserEmailsAndPhones(Page<UserDto> userDtoPage) startMethod, Page<UserDto>: {}",
                userDtoPage);
        for(UserDto user : userDtoPage) {
            setUserEmailsAndPhones(user);
        }
    }

    private void setUserEmailsAndPhones(UserDto userDto) {
        log.info("UserService:setUserEmailsAndPhones(UserDto userDto) startMethod, UserDto: {}", userDto);
        userDto.setEmailList(getUserEmails(userDto));
        userDto.setPhoneList(getUserPhones(userDto));
    }

    private List<String> getUserEmails(UserDto userDto) {
        log.info("UserService:getUserEmails startMethod, UserDto: {}", userDto);
        List<Email> emails = emailRepository.findAllByUserId(userDto.getId());
        ArrayList<String> emailList = new ArrayList<>();
        emails.stream().forEach(u -> {emailList.add(u.getEmail());});;
        log.info("UserService:getUserEmails endMethod, ArrayList<String> emailList: {}", emailList);
        return emailList;
    }

    private List<String> getUserPhones(UserDto userDto) {
        log.info("UserService:getUserPhones startMethod, UserDto: {}", userDto);
        List<Phone> phones = phoneRepository.findAllByUserId(userDto.getId());
        ArrayList<String> phoneList = new ArrayList<>();
        phones.stream().forEach(u -> {phoneList.add(u.getPhone());});;
        log.info("UserService:getUserPhones endMethod, ArrayList<String> emailList: {}", phoneList);
        return phoneList;
    }

    private BaseSearchDto getBaseSearchDto(){
        BaseSearchDto baseSearchDto = new BaseSearchDto();
        baseSearchDto.setIsDeleted(false);
        return  baseSearchDto;
    }

    /*
    private void getErrorIfNull(Object object) {
        if ((object == null)) {
            throw new AccountException("Нет данных пользователя");
        }
    }
    */


    ///////////////////////////////////////////////
    /** Ниже отрефакторить: */

    public AccountDto create(AccountDto accountDto) throws AccountException {
        log.info("AccountService:create() startMethod");
        /*
        Account account = mapperAccount.toEntity(accountDto);
        account.setRegistrationDate(LocalDateTime.now(ZoneId.of("Z")));
        account.setRoles(roleService.getRoleSet(Arrays.asList("USER", "MODERATOR")));
        account = accountRepository.save(account);
        return mapperAccount.toDto(account);
        */
        return null;
    }

    public AccountDto update(AccountDto accountDto) throws AccountException {
        log.info("AccountService:putMe() startMethod");
        /*
        Account account = accountDto.getId() != null ? accountRepository
                .findById(accountDto.getId()).get() : accountRepository.findById(AuthUtil.getUserId()).get();
        account = mapperAccount.rewriteEntity(accountRepository.findById(account.getId()).get(), accountDto);
        accountRepository.save(account);
        return mapperAccount.toDto(account);
                */
        return null;
    }

    public AccountDto getByEmail(String email) throws AccountException {
        log.info("AccountService:get(String email) startMethod");
        /*
        return mapperAccount.toDto(accountRepository.findFirstByEmail(email).orElseThrow(() -> new AccountException("BADREUQEST")));
        */
        return null;
    }

    public AccountDto getId(UUID uuid) throws AccountException {
        log.info("AccountService:get(String email) startMethod");
        /*
        return mapperAccount.toDto(accountRepository.findById(uuid).orElseThrow(() -> new AccountException("BADREUQEST")));
        */
        return null;
    }

    public AccountDto getMe() throws AccountException {
        log.info("AccountService: getMe() startMethod");
        /*
        return mapperAccount.toDto(accountRepository.findById(AuthUtil.getUserId()).orElseThrow(() -> new AccountException(BADREUQEST)));
        */
        return null;
    }

    /*
    public AccountDto changePassword(PasswordChangeDto passwordChangeDtoDto) {
        if (!passwordChangeDtoDto.getNewPassword1().equals(passwordChangeDtoDto.getNewPassword2())) {
            new AccountException("введенные пароли не совпадают");
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setPassword(passwordChangeDtoDto.getNewPassword1());
        accountDto.setId(AuthUtil.getUserId());
        return update(accountDto);
    }

    public AccountDto changeEmail(ChangeEmailDto changeEmailDto) {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(changeEmailDto.getEmail().getEmail());
        accountDto.setId(AuthUtil.getUserId());
        return update(accountDto);
    }
    */


    public Page<AccountDto> getAll(AccountSearchDto accountSearchDto, Pageable pageable) throws AccountException {
        /*
        Specification spec = SpecificationUtils.equal(Account_.COUNTRY, accountSearchDto.getCountry())
                .or(SpecificationUtils.like(Account_.FIRST_NAME, accountSearchDto.getFirstName()))
                .or(SpecificationUtils.like(Account_.LAST_NAME, accountSearchDto.getLastName()))
                .or(SpecificationUtils.like(Account_.CITY, accountSearchDto.getCity()))
                .or(SpecificationUtils.equal(Account_.EMAIL, accountSearchDto.getEmail()))
                .or(SpecificationUtils.between(Account_.BIRTH_DATE, accountSearchDto.getAgeFrom(), accountSearchDto.getAgeTo()))
                .or(SpecificationUtils.in(Account_.ID, accountSearchDto.getIds()));
        Page<Account> accounts = accountRepository.findAll(spec, pageable);
        return accounts.map(mapperAccount::toDto);
        */
        return null;
    }

    public AccountDto putMe(AccountDto accountDto) throws AccountException {
        log.info("AccountService:putMe() startMethod");
        /*
        return mapperAccount.toDto(mapperAccount.rewriteEntity(accountRepository.findById(AuthUtil.getUserId()).get(), accountDto));
        */
        return null;
    }

    public boolean delete() throws AccountException {
        log.info("AccountService:delete() startMethod");
        accountRepository.deleteById(AuthUtil.getUserId());
        return true;
    }

    public boolean deleteId(UUID id) throws AccountException {
        log.info("AccountService:deleteId() startMethod");
        accountRepository.deleteById(id);
        return true;
    }


    public Boolean doesAccountWithSuchEmailExist(String email) {
        /*
        return accountRepository.findFirstByEmail(email).isPresent();
        */
        return null;
    }

    private List<String> listOfRolesFromSetOfRoles(Set<Role> roles) {
        log.info("AccountService:listOfRolesFromSetOfRoles() startMethod");
        ArrayList<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getRole());
        }
        return roleNames;
    }

    public Account getAccountByEmail(String email) {
        /*
        return accountRepository.findFirstByEmail(email).orElse(null);
        */
        return null;
    }

    public void save(Account account) {
        accountRepository.save(account);
    }
}
