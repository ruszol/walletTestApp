package com.ruszol.wallettestapp.repository;

import com.ruszol.wallettestapp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    //Optional<Wallet> findWalletById(UUID walletId);

    Optional<Wallet> findWalletsByWalletId(UUID walletId);
}
