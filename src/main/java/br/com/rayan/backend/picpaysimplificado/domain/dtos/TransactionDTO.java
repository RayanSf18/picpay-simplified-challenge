package br.com.rayan.backend.picpaysimplificado.domain.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {}
