package ru.kolivim.estimates.calculator.domain.user;

import jakarta.persistence.*;
import lombok.*;
//import org.hibernate.envers.NotAudited;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;
import ru.kolivim.estimates.calculator.domain.role.Role;

import java.time.ZonedDateTime;
import java.util.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity /*BaseAuditedEntity*/ {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @NotAudited
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();


    /**
     is_deleted  boolean,
     id          uuid not null primary key,
     first_name  varchar(255),
     middle_name varchar(255),
     last_name   varchar(255),
     password    varchar(255),
     login       varchar(255)
     */

}
