package com.chest.wallet.repository;

import com.chest.wallet.domain.User;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Test user repository")
public class UserRepositoryTest {

  public static final String MARCEL_GHISI_GMAIL_COM = "marcel.ghisi@gmail.com";
  @Autowired
  UserRepository userRepository;

  @BeforeEach
  public void tearUp() {
    User user = User.builder().name("SetUp User").password("123456").email(MARCEL_GHISI_GMAIL_COM)
        .build();
    userRepository.save(user);
  }

  @AfterEach
  public void tearDown() {
    userRepository.deleteAll();
  }

  @Test
  public void testSaveUSer() {

    User user = User.builder()
        .name("Marcel")
        .email("marcel.ghisi@gmail.com")
        .password("123456")
        .build();

    User saved = userRepository.save(user);

    Assertions.assertNotNull(saved);

  }

  @Test
  public void testFindByEmail(){

    Optional<User> found = userRepository.findByEmailEquals(MARCEL_GHISI_GMAIL_COM);
    Assertions.assertTrue(found.isPresent());
    Assertions.assertEquals(found.get().getEmail(),MARCEL_GHISI_GMAIL_COM);


  }

}
