package com.marcosetm.user_management.controller;

import com.marcosetm.user_management.dto.AccountCreateDto;
import com.marcosetm.user_management.dto.AccountResponseDto;
import com.marcosetm.user_management.mapper.AccountMapper;
import com.marcosetm.user_management.model.Account;
import com.marcosetm.user_management.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Create Account
    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountCreateDto accountCreateReq) {
        Account account = AccountMapper.toEntity(accountCreateReq);
        Account createdAccount = accountService.createAccount(account);
        AccountResponseDto accountResponseDto = AccountMapper.toDto(createdAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponseDto);
    }
    // Read Account profile
    // todo: implement auth so only admin or logged-in user can request this information
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
