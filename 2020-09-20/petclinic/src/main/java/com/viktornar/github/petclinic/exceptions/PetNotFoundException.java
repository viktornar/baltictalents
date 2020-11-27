package com.viktornar.github.petclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PetNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public PetNotFoundException(String message) {
    super(message);
  }
}