package ru.kolivim.myproject.task.management.system.impl.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneRepository extends BaseRepository<Phone> /*JpaRepository<String Phone, UUID>*/ {
    int countByPhone(String phone);
    Optional<Phone> findByPhone(String phone);
    List<Phone> findAllByUserId(UUID userId);

}
