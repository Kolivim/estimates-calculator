package ru.kolivim.myproject.task.management.system.impl.repository.recoveryToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.passwordRecovery.RecoveryToken;

import java.util.UUID;
@Repository
public interface RecoveryTokenRepository extends JpaRepository<RecoveryToken, UUID> {
}
