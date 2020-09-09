package org.bookmark.domain.exception;

public class UrlExpiredException extends RuntimeException {

  public UrlExpiredException() {
    super("Url already expired.");
  }
}
