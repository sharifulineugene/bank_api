package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.dto.Balance;
import com.github.sharifulineugene.entity.Account;
import com.github.sharifulineugene.entity.Card;

import java.util.List;

public interface IAccountDao extends IDao<Account> {
    List<Card> getCardsByAccountId(int account_id);
    void addCardToAccount(Card card, int account_id);
    void addBalance(int id, Balance deposit);
}
