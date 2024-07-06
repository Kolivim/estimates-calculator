package ru.kolivim.myproject.task.management.system.impl.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.Email;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.domain.user.User;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailRepository extends BaseRepository<Email> /*JpaRepository<String Email, UUID> */ {
    int countByEmail(String email);
    Optional<Email> findByEmail(String email);
    List<Email> findAllByUserId(UUID userId);

}
