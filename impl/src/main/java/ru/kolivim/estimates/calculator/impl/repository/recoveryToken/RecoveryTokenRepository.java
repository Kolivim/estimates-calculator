package ru.kolivim.estimates.calculator.impl.repository.recoveryToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.estimates.calculator.domain.passwordRecovery.RecoveryToken;

import java.util.UUID;
@Repository
public interface RecoveryTokenRepository extends JpaRepository<RecoveryToken, UUID> {
}
