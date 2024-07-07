package ru.kolivim.estimates.calculator.impl.repository.account;

//import com.cloudinary.provisioning.Account;
import org.springframework.stereotype.Repository;

import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;
import ru.kolivim.estimates.calculator.domain.account.Account;

@Repository
public interface AccountRepository extends BaseRepository<Account> {
//    List<Account> findAllAndIsDeletedFalse();

//    Optional<Account> findFirstByEmail(String email);
}
