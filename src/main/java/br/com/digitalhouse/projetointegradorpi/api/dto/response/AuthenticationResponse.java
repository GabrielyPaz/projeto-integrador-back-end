package br.com.digitalhouse.projetointegradorpi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
  private final String jwt;
}
