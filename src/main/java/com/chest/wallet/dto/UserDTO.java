package com.chest.wallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDTO {

  private String id;

  @Email(message = "Email inválido")
  private String email;

  @Size(min = 3, max = 50, message = "Name 3 to 50")
  private String name;

  @NotNull
  @Size(min = 6,message = "min 6")
  private String password;

}
