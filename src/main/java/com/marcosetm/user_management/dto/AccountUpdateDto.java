package com.marcosetm.user_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AccountUpdateDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Must be a valid email")
    private String email;
    @Size(min = 8, max = 20)
    private String password;
    @Size(min = 8, max = 20)
    private String confirmPassword;
}
