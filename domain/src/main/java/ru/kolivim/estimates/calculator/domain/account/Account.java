package ru.kolivim.estimates.calculator.domain.account;

import jakarta.persistence.*;
import lombok.*;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;
import ru.kolivim.estimates.calculator.domain.user.User;
import java.time.ZonedDateTime;

//@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account extends User {

    @Column(name="last_online_time")
    private ZonedDateTime lastOnlineTime;

    @Column(name="about")
    private String about;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="position")
    private String position;

    @Column(name="department")
    private String department;

    @Column(name="personnel_number")
    private String personnelNumber;

    @Column(name="registration_date")
    private ZonedDateTime registrationDate;

    @Column(name="last_modified_date")
    private ZonedDateTime lastModifiedDate;


/** table :
    is_blocked         boolean,
    last_online_time   timestamp(6) with time zone,
    id                 uuid not null primary key,
    about              varchar(255),
    phone              varchar(255),
    email              varchar(255),
    position           varchar(255),
    department         varchar(255),
    personnel_number   varchar(255),
    registration_date  timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone
*/

}
