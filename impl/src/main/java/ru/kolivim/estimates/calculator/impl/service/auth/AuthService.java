package ru.kolivim.estimates.calculator.impl.service.auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
//import ru.kolivim.estimates.calculator.api.dto.auth.AuthenticateDto;
//import ru.kolivim.estimates.calculator.api.dto.auth.AuthenticateResponseDto;
//import ru.kolivim.estimates.calculator.api.dto.auth.RegistrationDto;
//import ru.kolivim.estimates.calculator.impl.repository.user.EmailRepository;
//import ru.kolivim.estimates.calculator.impl.service.account.AccountService;
//import ru.kolivim.estimates.calculator.impl.service.user.UserService;
//import ru.kolivim.estimates.calculator.impl.security.jwt.TokenGenerator;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

//    private final UserService userServices;
//    private final AccountService accountService;

//    private final AccountRepository accountRepository;
//    private final PhoneRepository phoneRepository;
//    private final EmailRepository emailRepository;
//
//    private final MapperAccount mapperAccount;
//    private final MapperAuthenticate mapperAuthenticate;
//    private final PhoneMapper phoneMapper;
//    private final EmailMapper emailMapper;
//
//    private final TokenGenerator tokenGenerator;

//    public AuthenticateResponseDto login(AuthenticateDto authenticateDto) {
////        JwtDto jwtDto = userServices.getJwtDto(authenticateDto);
////        if(jwtDto==null){return new AuthenticateResponseDto();}
//        AuthenticateResponseDto responseDto = new AuthenticateResponseDto();
////        responseDto.setAccessToken(tokenGenerator.createToken(jwtDto));
////        responseDto.setRefreshToken("Зарезервированное поле для refresh токена");
//        return responseDto;
//    }

//    public Boolean register(RegistrationDto registrationDto) {
//        log.info("AuthService: register(RegistrationDto registrationDto), registrationDto: {}",
//                registrationDto);
//
//        if(/*userServices.doesUserDataExist(registrationDto)*/ false){
//            log.info("AuthService: register(*), user data is available in the database, registrationDto: {}",
//                    registrationDto);
//            return false;
//        }

//        return accountService.create(registrationDto);

//        /** В SN еще допом было: */
        /*
        AccountDto accountDto = mapperAccount.accountDtoFromRegistrationDto(registrationDto);
        try {
            userServices.create(accountDto);
        } catch (AccountException e) {
            throw new RuntimeException(e);
        }
        */
//    }

}
