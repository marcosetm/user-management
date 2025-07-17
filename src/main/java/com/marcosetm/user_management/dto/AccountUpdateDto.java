package com.marcosetm.user_management.dto;

import jakarta.validation.constraints.Email;
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
    @Email
    private String email;
    @Size(min = 8, max = 20)
    private String password;
    @Size(min = 8, max = 20)
    private String confirmPassword;
}
