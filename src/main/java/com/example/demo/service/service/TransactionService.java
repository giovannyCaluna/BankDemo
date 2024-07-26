package com.example.demo.service.service;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.POJO.ReportLine;
import com.example.demo.service.exception.InsuficientBalance;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;

import java.util.*;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Transactional
    public Transaction calculateBalance(Transaction transaction) throws Exception {
        List<Transaction> transactionList = transactionRepository.findTransactionByAccount_AccountIdOrderByDateAsc(transaction.getAccount().getAccountId());
        if (transactionList.isEmpty()) {
            Optional<Account> account = accountRepository.findById(transaction.getAccount().getAccountId());
            if (account.isPresent()) {
                double balance = account.get().getInitialAmount() + transaction.getValue();
                if (balance < 0) {
                    throw new InsuficientBalance("The balance is not enough to perform the transaction");
                } else {
                    transaction.setAmount(account.get().getInitialAmount() + transaction.getValue());
                    transaction.setPreviousBalance(account.get().getInitialAmount());

                }
            }
        } else {
            double balance = transactionList.get(0).getAmount() + transaction.getValue();
            if (balance < 0) {
                throw new InsuficientBalance("The balance is not enough to perform the transaction");
            } else {
                transaction.setAmount(transactionList.get(0).getAmount() + transaction.getValue());
                transaction.setPreviousBalance(transactionList.get(0).getAmount());

            }
        }
        return transaction;
    }

    public Map<Long, List<ReportLine>> generateReport(Date startDate, Date endDate, Long clientId) throws Exception {
        List<Account> accounts = accountRepository.findByClientClientId(clientId);
        Map<Long, List<ReportLine>> reportData = new HashMap<>();
        List<ReportLine> reportLines = new ArrayList<>();
        for (Account account : accounts) {
            List<Transaction> transactions = transactionRepository.findTransactionByAccount_AccountIdAndDateBetweenOrderByDateDesc(account.getAccountId(), startDate, endDate);
            for (Transaction transaction : transactions) {
                ReportLine line = new ReportLine(
                        transaction.getDate(),
                        transaction.getAccount().getClient().getPerson().getName(),
                        transaction.getAccount().getAccountNumber(),
                        transaction.getAccount().getType(),
                        transaction.getPreviousBalance(),
                        transaction.getAccount().getState(),
                        transaction.getValue()>0? "Deposito de " + transaction.getValue().toString() : "Retiro de " + transaction.getValue()*-1 ,
                        transaction.getAmount());
                reportLines.add(line);
            }
            reportData.put(account.getAccountId(),reportLines);

        }
        return reportData;
    }
}
