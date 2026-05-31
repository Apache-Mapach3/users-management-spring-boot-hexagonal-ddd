package com.jcaa.usersmanagement.domain.exception;

public class DomainException extends RuntimeException {

  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable cause) {  // ← agregar este constructor
    super(message, cause);
  }
}