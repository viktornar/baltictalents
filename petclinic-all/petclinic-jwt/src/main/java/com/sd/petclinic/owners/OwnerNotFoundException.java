package com.sd.petclinic.owners;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public OwnerNotFoundException(String message) {
    super(message);
  }
}