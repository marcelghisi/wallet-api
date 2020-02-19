package com.chest.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chest.wallet.domain.User;
import com.chest.wallet.dto.UserDTO;
import com.chest.wallet.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import javax.print.attribute.standard.MediaSize.NA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  private static String ID = "123456";
  private static String EMAIL = "marcel.ghisi@gmail.com";
  private static String NAME = "Marcel Ghisi";
  private static String PASSWORD = "123456";
  private static String URL = "/user";

  @MockBean
  UserService userService;

  @Autowired
  MockMvc mockMvc;

  public User getMockUser(){
    return User.builder().id(123456L).email("marcel.ghisi@gmail.com").name(NAME).build();
  }
  public static String getJsonPayload(String id, String name, String email,String password) throws JsonProcessingException {
    UserDTO userDTO = UserDTO.builder().id(id).name(name).email(email).password(password).build();
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(userDTO);
  }

  @Test
  public void testNotNulUser() throws Exception {

    BDDMockito.given(userService.save(Mockito.any())).willReturn(Optional.of(getMockUser()));

    mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID,NAME,EMAIL,PASSWORD)).contentType(
        MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.id").value(ID))
        .andExpect(jsonPath("$.data.name").value(NAME))
        .andExpect(jsonPath("$.data.email").value(EMAIL))
        .andExpect(jsonPath("$.data.password").doesNotExist());
  }

  @Test
  public void testUserInvalid() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID,NAME,"EMAIL",PASSWORD)).contentType(
        MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[0]").value("Email inválido"));
  }

}
