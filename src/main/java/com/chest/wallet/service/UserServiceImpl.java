package com.chest.wallet.service;

import com.chest.wallet.domain.User;
import com.chest.wallet.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  UserRepository userRepository;

  public Optional<User> findByEmail(String s) {
    return userRepository.findByEmailEquals(s);
  }

  @Override
  public Optional<User> save(User convert) {
    return Optional.ofNullable(userRepository.save(convert));
  }
}
