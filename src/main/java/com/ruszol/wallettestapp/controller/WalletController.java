package com.ruszol.wallettestapp.controller;


import com.ruszol.wallettestapp.dto.WalletDto;
import com.ruszol.wallettestapp.dto.IncomingDto;
import com.ruszol.wallettestapp.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping("/wallets/{walletId}")
     public ResponseEntity<WalletDto> getBalance(@PathVariable UUID walletId){
        return  ResponseEntity.ok(walletService.getWalletBalance(walletId));
    }



    @PostMapping("/wallet")
    public ResponseEntity<WalletDto> makeOperation(@Valid @RequestBody IncomingDto incomingDto){
        return ResponseEntity.ok(walletService.makeOperation(incomingDto));
    }

}
