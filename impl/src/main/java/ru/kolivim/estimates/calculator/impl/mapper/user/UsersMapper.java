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

import java.time.ZonedDateTime;

@Slf4j
@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UsersMapper {

    public User toUser(UserDto userDto) {
        log.info("UsersMapper:toUser(UserDto userDto) startMethod - получен UserDto: {}", userDto);

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        user.setIsDeleted(false);
        user.setRegistrationDate(ZonedDateTime.now());
        user.setLastModifiedDate(ZonedDateTime.now());
        user.setIsBlocked(false);

        /** Остались не заполнеными: */
        // TODO: Определиться с механизмом заполнения - где и как, какое начальное значение присвоить
//        account.setAbout(accountDto.getAbout());
//        account.setPhone(accountDto.getPhone());
//        account.setEmail(accountDto.getEmail());
//        account.setPersonnelNumber(accountDto.getPersonnelNumber());

        log.info("UsersMapper:toUser(UserDto userDto) endMethod - получен к возврату User: {}", user);
        return user;
    }

    public UserDto toDto(User user) {
        log.info("UsersMapper:toDto(User user) startMethod - получен User: {}", user);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setIsDeleted(user.getIsDeleted());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());

        log.info("UsersMapper:toDto(User user) endMethod - получен к возврату UserDto: {}", userDto);
        return userDto;
    }


    /**
     * UserDTO:
     private UUID id;
     private boolean isDeleted;
     private String firstName;
     private String middleName;
     private String lastName;
     private String login;
     private String password;

     * User:
     private String firstName;
     private String middleName;
     private String lastName;
     private String login;
     private String password;
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