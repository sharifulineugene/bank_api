package com.github.sharifulineugene.dto.mappers;

import com.github.sharifulineugene.dto.AccountDto;
import com.github.sharifulineugene.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class AccountMapperTest {

    @Test
    public void shouldConvertDto() {
        Account account = new Account(1,"0000000000000000", null, 100L);
        AccountDto dtoExpected = new AccountDto();
        dtoExpected.setId(1);
        dtoExpected.setNumber("0000000000000000");
        dtoExpected.setBalance(100L);
        AccountDto dtoActual = new AccountMapper(new ModelMapper()).toDto(account);
        Assertions.assertEquals(dtoExpected,dtoActual);
    }

    @Test
    public void shouldConvertEntity() {
        Account entityExpected = new Account(1,"0000000000000000", null, 100L);
        AccountDto dto = new AccountDto();
        dto.setId(1);
        dto.setNumber("0000000000000000");
        dto.setBalance(100L);
        Account entityActual = new AccountMapper(new ModelMapper()).toEntity(dto);
        Assertions.assertEquals(entityExpected,entityActual);
    }




}
