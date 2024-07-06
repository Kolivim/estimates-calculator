package ru.kolivim.myproject.task.management.system.impl.repository.captcha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.captcha.Captcha;

import java.util.UUID;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha, UUID> {
}
