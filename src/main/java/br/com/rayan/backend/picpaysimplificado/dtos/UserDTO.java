package br.com.rayan.backend.picpaysimplificado.dtos;

import br.com.rayan.backend.picpaysimplificado.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String document,
        @Email String email,
        @NotBlank String password,
        @NotNull UserType type,
        @Min(0) BigDecimal balance
) { }
