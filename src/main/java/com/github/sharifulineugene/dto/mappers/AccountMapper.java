package com.github.sharifulineugene.dto.mappers;

import com.github.sharifulineugene.dto.AccountDto;
import com.github.sharifulineugene.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountMapper {
    private ModelMapper mapper;

    @Autowired
    public AccountMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Account toEntity(AccountDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto,Account.class);
    }

    public AccountDto toDto(Account entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AccountDto.class);
    }
}
