package com.chest.wallet.service;

import com.chest.wallet.domain.User;
import com.chest.wallet.domain.Wallet;
import java.util.Optional;

public interface WalletService {
  Optional<Wallet> save(Wallet wallet);
}
