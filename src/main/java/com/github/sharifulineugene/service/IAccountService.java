package com.github.sharifulineugene.service;

import com.github.sharifulineugene.dto.AccountDto;
import com.github.sharifulineugene.dto.Balance;
import com.github.sharifulineugene.dto.CardDto;

import java.util.List;

public interface IAccountService extends Service<AccountDto>{
    List<CardDto> getCardsByAccountId(int account_id);
    void addCardToAccount(CardDto card, int account_id);
    void addBalance(int id, Balance deposit);
}
