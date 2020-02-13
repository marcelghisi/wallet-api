package com.chest.wallet.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Response<T> {

  public Response(){
    this.errors = new ArrayList<>();
  }

  T data;
  List<String> errors;

}
