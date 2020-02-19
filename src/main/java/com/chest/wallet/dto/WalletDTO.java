package com.chest.wallet.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class WalletDTO {

  private Long id;

  @Length(min = 3)
  private String name;

  @NotNull
  private BigDecimal value;

}
