package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByAccount_AccountIdOrderByDateAsc(Long accountId);
    List<Transaction> findTransactionByAccount_AccountIdAndDateBetweenOrderByDateDesc(Long accountId, Date startDate, Date endDate);
}
