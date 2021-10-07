package com.github.sharifulineugene.service;


import com.github.sharifulineugene.dao.IAccountDao;
import com.github.sharifulineugene.dto.AccountDto;
import com.github.sharifulineugene.dto.Balance;
import com.github.sharifulineugene.dto.CardDto;
import com.github.sharifulineugene.dto.mappers.AccountMapper;
import com.github.sharifulineugene.dto.mappers.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional()
public class AccountService implements IAccountService{

    private IAccountDao accountDAO;
    private AccountMapper accountMapper;
    private CardMapper cardMapper;

    @Autowired
    public AccountService(IAccountDao accountDAO, AccountMapper accountMapper, CardMapper cardMapper) {
        this.accountDAO = accountDAO;
        this.accountMapper = accountMapper;
        this.cardMapper = cardMapper;
    }
    @Transactional(readOnly = true)
    @Override
    public List<AccountDto> getAll() {
        return accountDAO.index().stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(AccountDto object) {
        accountDAO.save(accountMapper.toEntity(object));
    }

    @Override
    public void update(AccountDto object) {
        accountDAO.update(accountMapper.toEntity(object));
    }

    @Transactional(readOnly = true)
    @Override
    public AccountDto get(int id) {
        return accountMapper.toDto(accountDAO.show(id));
    }

    @Override
    public void delete(int id) {
        accountDAO.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CardDto> getCardsByAccountId(int account_id) {
        return accountDAO.getCardsByAccountId(account_id).stream().map(cardMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void addCardToAccount(CardDto card, int account_id) {
        accountDAO.addCardToAccount(cardMapper.toEntity(card), account_id);
    }

    @Override
    public void addBalance(int id, Balance deposit) {
        accountDAO.addBalance(id, deposit);
    }
}
