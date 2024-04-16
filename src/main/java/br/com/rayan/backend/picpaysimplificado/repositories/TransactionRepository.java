package br.com.rayan.backend.picpaysimplificado.repositories;

import br.com.rayan.backend.picpaysimplificado.domain.transaction.Transaction;
import br.com.rayan.backend.picpaysimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
