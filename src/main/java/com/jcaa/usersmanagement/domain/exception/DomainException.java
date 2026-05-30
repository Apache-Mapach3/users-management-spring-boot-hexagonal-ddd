package com.jcaa.usersmanagement.domain.exception;

public class DomainException extends RuntimeException { // <-- Clave: RuntimeException
  public DomainException(String message) {
    super(message);
  }
}