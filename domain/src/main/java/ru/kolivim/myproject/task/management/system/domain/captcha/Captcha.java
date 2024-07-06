package ru.kolivim.myproject.task.management.system.domain.captcha;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;

import java.time.LocalDateTime;

@Table(name = "captcha")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Captcha extends BaseEntity {
    @Column
    LocalDateTime expirationTime;
    @Column
    String answer;
}
