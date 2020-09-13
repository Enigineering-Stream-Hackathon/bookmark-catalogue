package org.bookmark.domain.enitites;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Card {
  private String cardId;
  private String title;
  private String description;
  private String longUrl;
  @Setter
  private String shortUrl;
  private String creator;
  private LocalDate createdOn;
  private String context;
  private String featureTeam;
  private String tribe;
  private String platform;
}
