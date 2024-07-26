package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Client;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Account> accounts = accountRepository.findAll();
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getTutorialById(@PathVariable("id") long id) {
        Optional<Account> account =  accountRepository.findById(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createClient(@RequestBody Account account) {
        try {
            Optional<Client> client =  clientRepository.findById(account.getClient().getClientId());
            if(client.isPresent()){
                Account newAccount = new Account();
                newAccount.setAccountNumber(account.getAccountNumber());
                newAccount.setClient(client.get());
                newAccount.setType(account.getType());
                newAccount.setInitialAmount(account.getInitialAmount());
                newAccount.setState(account.getState());
                newAccount = this.accountRepository.save(newAccount);
                return new ResponseEntity<>(newAccount, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateTutorial(@PathVariable("id") long id, @RequestBody Account account) {
        Optional<Account> accountData = accountRepository.findById(id);
        if (accountData.isPresent()) {
            Account mAccount = accountData.get();
            mAccount.setState(account.getState());
            mAccount.setAccountNumber(account.getAccountNumber());
            mAccount.setInitialAmount(account.getInitialAmount());
            mAccount.setType(account.getType());
            return new ResponseEntity<>(accountRepository.save(mAccount), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
