package com.github.sharifulineugene.controllers;

import com.github.sharifulineugene.dto.AccountDto;
import com.github.sharifulineugene.dto.Balance;
import com.github.sharifulineugene.dto.CardDto;
import com.github.sharifulineugene.excception_handling.account.AccountIncorrectData;
import com.github.sharifulineugene.excception_handling.account.AddBalanceValueIncorrect;
import com.github.sharifulineugene.excception_handling.account.AddBalanceValueIncorrectException;
import com.github.sharifulineugene.excception_handling.account.NoSuchAccountException;
import com.github.sharifulineugene.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final IAccountService service;


    @Autowired
    public AccountController(IAccountService service) {
        this.service = service;
    }

    @ExceptionHandler
    public ResponseEntity<AccountIncorrectData> handleExceptionNoSuchAccount(
            NoSuchAccountException ex) {
        AccountIncorrectData data = new AccountIncorrectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<AccountIncorrectData>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AddBalanceValueIncorrect> handleExceptionBalanceValue(AddBalanceValueIncorrectException ex) {
        AddBalanceValueIncorrect data = new AddBalanceValueIncorrect();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<AddBalanceValueIncorrect>(data, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }

    @GetMapping("")
    public List<AccountDto> showAllAccount() {
        List<AccountDto> all = service.getAll();
        return all;
    }
    @GetMapping("/{id}")
    public AccountDto show(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping("/{id}/cards")
    public List<CardDto> showAllCardsFromAccount(@PathVariable("id") int id) {
        List<CardDto> cards = service.getCardsByAccountId(id);
        return cards;
    }

    @GetMapping("/{id}/balance")
    public Balance showBalance(@PathVariable("id") int id) {
        AccountDto account = service.get(id);
        Balance balance = new Balance();
        balance.setBalance(account.getBalance());
        return balance;
    }

    @PostMapping("")
    public AccountDto addNewAccount(@RequestBody AccountDto account) {
        service.save(account);
        return account;
    }

    @PutMapping("")
    public AccountDto updateAccount(@RequestBody AccountDto account) {
        throw new UnsupportedOperationException("method not supported yet");

    }

    @PostMapping("{id}/newcard")
    public ResponseEntity<Void> newCardByIdAccount(@PathVariable("id") int id,@RequestBody CardDto card) {
        service.addCardToAccount(card,id);
        String redirect = "/accounts/"+id+"/cards";
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirect)).build();
    }

    @PutMapping ("{id}")
    public ResponseEntity<Void> addBalance(@PathVariable("id") int id, @RequestBody Balance deposit) {
        if(deposit == null || deposit.getBalance() < 0)
            throw new AddBalanceValueIncorrectException("value of deposit < 0 or equals 'null'");
        service.addBalance(id,deposit);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/accounts/"+id+"/balance")).build();
    }

}
