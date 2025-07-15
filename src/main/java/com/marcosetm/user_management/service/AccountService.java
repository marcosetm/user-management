package com.marcosetm.user_management.service;

import com.marcosetm.user_management.dto.AccountResponseDto;
import com.marcosetm.user_management.mapper.AccountMapper;
import com.marcosetm.user_management.model.Account;
import com.marcosetm.user_management.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
    public Optional<AccountResponseDto> getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::toDto);
    }
}
