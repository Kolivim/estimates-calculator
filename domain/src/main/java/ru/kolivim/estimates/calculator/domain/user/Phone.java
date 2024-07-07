package ru.kolivim.estimates.calculator.domain.user;

import jakarta.persistence.*;
import lombok.*;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone")
//@Embeddable
public class Phone extends BaseEntity {

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_id")
    private UUID userId;


//    @ManyToOne
//    private User user;

}
