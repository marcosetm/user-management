package com.marcosetm.user_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginRequestDto {
    @NotBlank(message = "Email is required")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Must be a valid email")
    private String email;
    @NotBlank
    private String password;
}
