package ru.kolivim.myproject.task.management.system.domain.user;

import jakarta.persistence.*;
import lombok.*;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email")
//@Embeddable
public class Email extends BaseEntity{

    @Column(name = "email")
    private String email;

    @Column(name = "user_id")
    private UUID userId;


//    @ManyToOne
//    private User user;
}