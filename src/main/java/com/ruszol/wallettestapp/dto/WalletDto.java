package com.ruszol.wallettestapp.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record WalletDto(UUID walletDtoId, BigDecimal balance) {
}
