package org.bookmark.domain.commands;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlCommand {

  public String longUrl;
  private LocalDate expiryDate;


}
