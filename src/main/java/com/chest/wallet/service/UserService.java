package com.chest.wallet.service;

import com.chest.wallet.domain.User;
import java.util.Optional;

public interface UserService {
  public Optional<User> findByEmail(String s);

  Optional<User> save(User convert);
}
