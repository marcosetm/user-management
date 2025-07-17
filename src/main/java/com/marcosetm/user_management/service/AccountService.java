package com.marcosetm.user_management.service;

import com.marcosetm.user_management.dto.AccountResponseDto;
import com.marcosetm.user_management.dto.AccountUpdateDto;
import com.marcosetm.user_management.mapper.AccountMapper;
import com.marcosetm.user_management.mapper.AccountUpdateMapper;
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
    private final AccountUpdateMapper accountUpdateMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          PasswordEncoder passwordEncoder,
                          AccountUpdateMapper accountUpdateMapper) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountUpdateMapper = accountUpdateMapper;
    }

    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
    public Optional<AccountResponseDto> getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::toDto);
    }
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }
    public boolean authenticate(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public boolean updateAccount(Long id, AccountUpdateDto accountUpdateReq) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            accountUpdateMapper.updateAccountFromDto(accountUpdateReq, account.get());

            if (accountUpdateReq.getPassword() != null) {
                if (!accountUpdateReq.getPassword().equals(accountUpdateReq.getConfirmPassword())) {
                    throw new IllegalArgumentException("Passwords do not match");
                }
                account.get().setPassword(passwordEncoder.encode(accountUpdateReq.getPassword()));
            }

            accountRepository.save(account.get());
            return true;
        }
        return false;
    }
}
