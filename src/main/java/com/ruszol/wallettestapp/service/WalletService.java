package com.ruszol.wallettestapp.service;


import com.ruszol.wallettestapp.dto.WalletDto;
import com.ruszol.wallettestapp.dto.IncomingDto;
import com.ruszol.wallettestapp.entity.Wallet;
import com.ruszol.wallettestapp.exception.InsufficentBalanceException;
import com.ruszol.wallettestapp.exception.InvalidOperationException;
import com.ruszol.wallettestapp.exception.WalletNotFoundException;
import com.ruszol.wallettestapp.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class WalletService {


    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    private final WalletRepository walletRepository;


    private Wallet findWallet(UUID WalletId) {
        Optional<Wallet> optionalWallet = walletRepository.findWalletsByWalletId(WalletId);
        if (optionalWallet.isPresent()) {
            return optionalWallet.get();
        }else  {
            throw new WalletNotFoundException("Кошелек не найден");
        }
    }

    @Transactional(readOnly = true)
    public WalletDto getWalletBalance(UUID walletId) {
        Wallet wallet = findWallet(walletId);
        log.info("Найден кошелек с идентификатором {}", walletId);
            return WalletDto.builder()
                    .walletDtoId(wallet.getWalletId())
                    .balance(wallet.getBalance())
                    .build();
        }



    @Transactional
    public WalletDto makeOperation(IncomingDto incomingDto) {

        Wallet wallet = walletRepository.findWalletsByWalletId(incomingDto.walletDtoId())
                .orElseThrow(()->new WalletNotFoundException("Кошелек не найден"));

        switch (incomingDto.operationType()) {
            case DEPOSIT:
                wallet.setBalance(wallet.getBalance().add(incomingDto.amount()));
                log.info("Внесен депозит в размере: {}", incomingDto.amount());
                break;
            case WITHDRAW:
                if (incomingDto.amount().compareTo(wallet.getBalance()) > 0) {
                    log.info("Недостаточно средств для совершения операции");
                    throw new InsufficentBalanceException("Недостаточно средств для совершения операции");
                }
                wallet.setBalance(wallet.getBalance().subtract(incomingDto.amount()));
                log.info("Снятие со счета: {}", incomingDto.amount());
                break;
            default:
                throw new InvalidOperationException("Операция не распознана");
        }
        walletRepository.saveAndFlush(wallet);
            return WalletDto.builder()
                    .walletDtoId(wallet.getWalletId())
                    .balance(wallet.getBalance())
                    .build();
    }
}
