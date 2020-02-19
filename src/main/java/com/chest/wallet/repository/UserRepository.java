package com.chest.wallet.repository;

import com.chest.wallet.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailEquals(String email);
}
