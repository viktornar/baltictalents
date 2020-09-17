package com.sd.petclinic.visits;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VisitNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public VisitNotFoundException(String message) {
    super(message);
  }
}