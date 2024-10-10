package net.javaguides.banking_app.repository;

import net.javaguides.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
// accepts entity and primary key long data type
public interface AccountRepository extends JpaRepository<Account,Long> {
}
