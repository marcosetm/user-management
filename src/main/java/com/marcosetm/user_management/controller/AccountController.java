package com.marcosetm.user_management.controller;

import com.marcosetm.user_management.dto.AccountCreateDto;
import com.marcosetm.user_management.dto.AccountLoginRequestDto;
import com.marcosetm.user_management.dto.AccountResponseDto;
import com.marcosetm.user_management.dto.AccountUpdateDto;
import com.marcosetm.user_management.mapper.AccountMapper;
import com.marcosetm.user_management.model.Account;
import com.marcosetm.user_management.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // Read Account
    // todo: implement auth so only admin or logged-in user can request this information
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Update Account
    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable Long id,
                                                            @Valid @RequestBody AccountUpdateDto accountUpdateReq) {
        boolean isUpdated = accountService.updateAccount(id, accountUpdateReq);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return accountService.getAccountById(id)
                .map(updatedDto -> ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDto))
                .orElse(ResponseEntity.notFound().build());
    }
    // Delete Account
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        boolean isDeleted = accountService.deleteAccountById(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AccountLoginRequestDto accountLoginReq) {
        Optional<Account> accountOptional = accountService.getAccountByEmail(accountLoginReq.getEmail());

        if (accountOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Account account = accountOptional.get();
        boolean isAuthenticated = accountService.authenticate(accountLoginReq.getPassword(), account.getPassword());

        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(AccountMapper.toDto(account));
    }
}
