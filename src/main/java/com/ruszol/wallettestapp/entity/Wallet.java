package com.ruszol.wallettestapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id", unique = true, nullable = false)
    private UUID walletId;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

}


