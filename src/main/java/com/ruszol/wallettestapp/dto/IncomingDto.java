package com.ruszol.wallettestapp.dto;

import com.ruszol.wallettestapp.entity.OperationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record IncomingDto(@NotNull UUID walletDtoId, @NotNull OperationType operationType, @Positive @NotNull BigDecimal amount) {
}
