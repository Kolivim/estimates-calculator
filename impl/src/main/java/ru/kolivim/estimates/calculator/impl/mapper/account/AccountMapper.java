package ru.kolivim.estimates.calculator.impl.mapper.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.kolivim.estimates.calculator.api.dto.account.AccountDto;
import ru.kolivim.estimates.calculator.domain.account.Account;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public abstract class AccountMapper {

    public Account toAccount(AccountDto accountDto) {
        log.info("AccountMapper:toAccount(AccountDto accountDto) startMethod - получен AccountDto: {}", accountDto);

        Account account = new Account();
        account.setLastOnlineTime(accountDto.getLastOnlineTime());
        account.setAbout(accountDto.getAbout());
        account.setPhone(accountDto.getPhone());
        account.setEmail(accountDto.getEmail());
        account.setPosition(accountDto.getPosition());
        account.setDepartment(accountDto.getDepartment());
        account.setPersonnelNumber(accountDto.getPersonnelNumber());
        account.setRegistrationDate(accountDto.getRegistrationDate());
        account.setLastModifiedDate(accountDto.getLastModifiedDate());

        log.info("AccountMapper:toAccount(_) toEndMethod - получен к возврату Account: {}", account);
        return account;
    }

    public AccountDto toDto(Account account) {
        log.info("AccountMapper:toDto(Account account) startMethod - получен Account: {}", account);

        AccountDto accountDto = new AccountDto();
        accountDto.setLastOnlineTime(account.getLastOnlineTime());
        accountDto.setAbout(account.getAbout());
        accountDto.setPhone(account.getPhone());
        accountDto.setEmail(account.getEmail());
        accountDto.setPosition(account.getPosition());
        accountDto.setDepartment(account.getDepartment());
        accountDto.setPersonnelNumber(account.getPersonnelNumber());
        accountDto.setRegistrationDate(account.getRegistrationDate());
        accountDto.setLastModifiedDate(account.getLastModifiedDate());

        log.info("AccountMapper:toDto(_) toEndMethod - получен к возврату AccountDto: {}", accountDto);
        return accountDto;
    }


    /**
     * AccountDTO:
     private ZonedDateTime lastOnlineTime;
     private String about;
     private String phone;
     private String email;
     private String position;
     private String department;
     private String personnelNumber;
     private ZonedDateTime registrationDate;
     private ZonedDateTime lastModifiedDate;

     * Account:
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


//    public List<UUID> getListUUID(List<String> stringListUUIDs) {
//        log.info("NotificationsMapper:getListUUI() startMethod - получен List<String>: {}", stringListUUIDs);
//        List<UUID> listUUIDs = new ArrayList<>();
//        for (String stringId : stringListUUIDs) {
//            listUUIDs.add(UUID.fromString(stringId));
//        }
//        log.info("NotificationsMapper:getListUUI() endMethod - итоговый List<UUID>: {}", listUUIDs);
//        return listUUIDs;
//    }

//    public NotificationDTO getNotificationDTO(Message message) {
//        log.info("NotificationsMapper:getNotificationDTO(Message message) начало метода - передан friend: {}", message);
//
//        NotificationDTO notificationDTO = new NotificationDTO();
//        notificationDTO.setAuthorId(message.getConversationPartner1());
//        notificationDTO.setReceiverId(message.getConversationPartner2());
//        notificationDTO.setSentTime(message.getTime());
//        notificationDTO.setContent(message.getMessageText());
//        notificationDTO.setNotificationType(Type.MESSAGE);
//
//        log.info("NotificationsMapper:getNotificationDTO(Friend friend) конец метода - получен NotificationDTO: {}",
//                notificationDTO);
//        return notificationDTO;
//    }

}