package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.POJO.ReportLine;
import com.example.demo.service.service.ClientService;
import com.example.demo.service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@RestController
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
@Autowired
private TransactionService transactionService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTutorialById(@PathVariable("id") long id) {
        Optional<Transaction> transaction =  transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity createClient(@RequestBody Transaction transaction) {
        Transaction newTransaction = new Transaction();

        try {
            newTransaction.setType(transaction.getType());
            newTransaction.setAmount(transaction.getPreviousBalance()+transaction.getValue());
            newTransaction.setDate(transaction.getDate());
            newTransaction.setValue(transaction.getValue());
            newTransaction.setPreviousBalance(transaction.getPreviousBalance());
            Optional<Account> acc = accountRepository.findById(transaction.getAccount().getAccountId());
            if (acc.isPresent()) {
                newTransaction.setAccount(acc.get());
                newTransaction = transactionRepository.save(newTransaction);
                return new ResponseEntity<>(newTransaction, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateTutorial(@PathVariable("id") long id, @RequestBody Transaction transaction) {
        Optional<Transaction> transactionData = transactionRepository.findById(id);
        if (transactionData.isPresent()) {
            Transaction mTransaction = transactionData.get();
            mTransaction.setAmount(transaction.getAmount());
            mTransaction.setDate(transaction.getDate());
            mTransaction.setValue(transaction.getValue());
            mTransaction.setType(transaction.getType());

            return new ResponseEntity<>(transactionRepository.save(mTransaction), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/movimiento")
    public ResponseEntity<Transaction> newTransaction(@RequestBody Transaction transaction) throws Exception {
        Transaction mtransaction = transactionService.calculateBalance(transaction);
        return new ResponseEntity<>(transactionRepository.save(mtransaction), HttpStatus.CREATED);

    }
    @GetMapping("/movimiento/{accountId}")
    public ResponseEntity<List<Transaction>> getHistorical(@PathVariable("accountId") long accountId) {
        try {
            List<Transaction> historical = transactionRepository.findTransactionByAccount_AccountIdOrderByDateAsc(accountId);
            return new ResponseEntity<>(historical, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

        @GetMapping("/reportes")
        public Map<Long, List<ReportLine>> getReportes(@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate[] fecha,
                                               @RequestParam("clientId") Long id) throws Exception {

            if (fecha.length != 2) {
                throw new IllegalArgumentException("The fecha parameter must contain a start and end date");
            }

            Timestamp startDate = Timestamp.valueOf(fecha[0].atStartOfDay().plusDays(1));
            Timestamp endDate =  Timestamp.valueOf(fecha[1].atStartOfDay().plusDays(1));
            return transactionService.generateReport(startDate,endDate,id);
        }







}
