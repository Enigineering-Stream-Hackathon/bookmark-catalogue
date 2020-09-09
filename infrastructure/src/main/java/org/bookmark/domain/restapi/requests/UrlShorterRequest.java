package org.bookmark.domain.restapi.requests;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookmark.domain.commands.UrlCommand;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UrlShorterRequest {
  public String longUrl;
  private LocalDate expiryDate;

  public UrlCommand toCommand() {
    return new UrlCommand(longUrl, expiryDate);
  }
}
