package ru.kolivim.estimates.calculator.impl.repository.account;

import org.springframework.stereotype.Repository;
import ru.kolivim.estimates.calculator.domain.account.Account;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {

    Optional<Account> findFirstByEmail(String email);
}
