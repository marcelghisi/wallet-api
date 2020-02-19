package com.chest.wallet.controller;

import com.chest.wallet.domain.Wallet;
import com.chest.wallet.dto.WalletDTO;
import com.chest.wallet.response.Response;
import com.chest.wallet.service.WalletService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wallet")
public class WalletController {

  @Autowired
  WalletService walletService;

  @PostMapping
  public ResponseEntity<Response<WalletDTO>> createWallet(@Valid @RequestBody WalletDTO dto,
      BindingResult bindingResult) {

    Response<WalletDTO> response = new Response<>();

    Optional<Wallet> savedWallet = walletService.save(convert(dto));

    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().stream()
          .forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }

    WalletDTO walletDTO = convert(savedWallet.get());
    response.setData(walletDTO);
    return ResponseEntity.ok(response);
  }

  public static WalletDTO convert(Wallet wallet) {
    return WalletDTO.builder().id(wallet.getId()).name(wallet.getName()).value(wallet.getValue())
        .build();
  }

  public static Wallet convert(WalletDTO wallet) {
    return Wallet.builder().id(wallet.getId()).name(wallet.getName()).value(wallet.getValue())
        .build();
  }


}
