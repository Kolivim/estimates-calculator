package ru.kolivim.estimates.calculator.domain.passwordRecovery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kolivim.estimates.calculator.domain.account.Account;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;

import java.time.LocalDateTime;

@Table(name = "recover_token")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryToken extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column
    LocalDateTime expirationTime;
}
