package ru.kolivim.estimates.calculator.impl.repository.user;

import org.springframework.stereotype.Repository;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;
import ru.kolivim.estimates.calculator.domain.user.Phone;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneRepository extends BaseRepository<Phone> /*JpaRepository<String Phone, UUID>*/ {
    int countByPhone(String phone);
    Optional<Phone> findByPhone(String phone);
    List<Phone> findAllByUserId(UUID userId);

}
