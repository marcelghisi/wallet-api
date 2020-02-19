package com.chest.wallet.controller;

import com.chest.wallet.domain.User;
import com.chest.wallet.dto.UserDTO;
import com.chest.wallet.response.Response;
import com.chest.wallet.service.UserService;
import com.chest.wallet.util.BCrypto;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping
  public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO user, BindingResult result){
    Response<UserDTO> userResponse = new Response<>();

    if (result.hasErrors()){
      result.getAllErrors().forEach(error -> userResponse.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(userResponse);
    }

    Optional<User> userSaved = userService.save(convert(user));
    userResponse.setData(convert(userSaved.get()));
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }

  private User convert(UserDTO user){
    return User.builder().email(user.getEmail()).password(BCrypto.getHash(user.getPassword())).name(user.getName()).build();
  }
  private UserDTO convert(User user){
    return UserDTO.builder().id(user.getId().toString()).email(user.getEmail()).name(user.getName()).build();
  }

}
