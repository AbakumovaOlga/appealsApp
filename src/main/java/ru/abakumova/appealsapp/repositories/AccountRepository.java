package ru.abakumova.appealsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abakumova.appealsapp.models.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
