package ru.kolivim.estimates.calculator.impl.repository.user;

import org.springframework.stereotype.Repository;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;
import ru.kolivim.estimates.calculator.domain.user.Email;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailRepository extends BaseRepository<Email> /*JpaRepository<String Email, UUID> */ {
    int countByEmail(String email);
    Optional<Email> findByEmail(String email);
    List<Email> findAllByUserId(UUID userId);

}
