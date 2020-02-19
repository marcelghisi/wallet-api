package com.chest.wallet.service;

import com.chest.wallet.domain.Wallet;
import com.chest.wallet.repository.WalletRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

  @Autowired
  WalletRepository walletRepository;

  @Override
  public Optional<Wallet> save(Wallet wallet){
    return Optional.ofNullable(walletRepository.save(wallet));
  }

}
