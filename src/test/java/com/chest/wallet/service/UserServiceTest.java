package com.chest.wallet.service;

import com.chest.wallet.domain.User;
import com.chest.wallet.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@DisplayName("Test User service")
public class UserServiceTest {


  @MockBean
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @BeforeEach
  public void setup(){
    BDDMockito.given(userRepository.findByEmailEquals(Mockito.anyString())).willReturn(
        Optional.of(new User()));
  }

  @Test
  public void test1(){
    Optional<User> user = userService.findByEmail("marcel.ghisi@gmail.com");
    Assertions.assertTrue(user.isPresent());
  }

}
