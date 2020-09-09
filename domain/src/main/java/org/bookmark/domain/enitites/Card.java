package org.bookmark.domain.enitites;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {
  private String cardId;
  private String title;
  private String description;
  private String longUrl;
  private String shortUrl;
  private String creator;
  private LocalDate createdOn;
}
