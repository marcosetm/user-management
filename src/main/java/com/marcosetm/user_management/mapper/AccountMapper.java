package com.marcosetm.user_management.mapper;

import com.marcosetm.user_management.dto.AccountCreateDto;
import com.marcosetm.user_management.dto.AccountResponseDto;
import com.marcosetm.user_management.model.Account;

public class AccountMapper {

    public static Account toEntity(AccountCreateDto accountCreateDto) {
        Account account = new Account();
        account.setFirstName(accountCreateDto.getFirstName());
        account.setLastName(accountCreateDto.getLastName());
        account.setDateOfBirth(accountCreateDto.getDateOfBirth());
        account.setEmail(accountCreateDto.getEmail());
        account.setPassword(accountCreateDto.getPassword());
        account.setRole(accountCreateDto.getRole());
        return account;
    }
    public static AccountResponseDto toDto(Account account) {
        return new AccountResponseDto(
                account.getId(),
                account.getFirstName(),
                account.getLastName(),
                account.getDateOfBirth(),
                account.getEmail()
        );
    }
}
