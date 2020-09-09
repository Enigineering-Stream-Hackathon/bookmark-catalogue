package org.bookmark.domain.enitites;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlEntity {

  private String id;
  private String longUrl;
  private LocalDate expiryDate;
  private LocalDate creationDate;

}
