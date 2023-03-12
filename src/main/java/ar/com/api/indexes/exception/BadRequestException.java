package ar.com.api.indexes.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequestException extends Exception {
 
 public BadRequestException(String message) {
  super(message);
 }

}
