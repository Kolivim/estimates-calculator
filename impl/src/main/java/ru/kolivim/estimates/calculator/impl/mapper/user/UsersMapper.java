package ru.kolivim.estimates.calculator.impl.mapper.user;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import ru.kolivim.estimates.calculator.api.dto.account.AccountDto;
import ru.kolivim.estimates.calculator.api.dto.user.UserDto;
import ru.kolivim.estimates.calculator.domain.account.Account;
import ru.kolivim.estimates.calculator.domain.user.User;

@Slf4j
@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UsersMapper {

    public User toUser(UserDto userDto) {
        log.info("UsersMapper:toUser(UserDto userDto) startMethod - получен UserDto: {}", userDto);

        User user = new User();
//        account.setLastOnlineTime(accountDto.getLastOnlineTime());
//        account.setAbout(accountDto.getAbout());
//        account.setPhone(accountDto.getPhone());
//        account.setEmail(accountDto.getEmail());
//        account.setPosition(accountDto.getPosition());
//        account.setDepartment(accountDto.getDepartment());
//        account.setPersonnelNumber(accountDto.getPersonnelNumber());
//        account.setRegistrationDate(accountDto.getRegistrationDate());
//        account.setLastModifiedDate(accountDto.getLastModifiedDate());

        log.info("UsersMapper:toUser(UserDto userDto) endMethod - получен к возврату User: {}", user);
        return user;
    }

    public UserDto toDto(User user) {
        log.info("UsersMapper:toDto(User user) startMethod - получен User: {}", user);

        UserDto userDto = new UserDto();
//        accountDto.setLastOnlineTime(account.getLastOnlineTime());
//        accountDto.setAbout(account.getAbout());
//        accountDto.setPhone(account.getPhone());
//        accountDto.setEmail(account.getEmail());
//        accountDto.setPosition(account.getPosition());
//        accountDto.setDepartment(account.getDepartment());
//        accountDto.setPersonnelNumber(account.getPersonnelNumber());
//        accountDto.setRegistrationDate(account.getRegistrationDate());
//        accountDto.setLastModifiedDate(account.getLastModifiedDate());

        log.info("UsersMapper:toDto(User user) endMethod - получен к возврату UserDto: {}", userDto);
        return userDto;
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