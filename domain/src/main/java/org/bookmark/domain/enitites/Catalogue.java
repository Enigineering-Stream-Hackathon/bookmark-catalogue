package org.bookmark.domain.enitites;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Catalogue {
  private String catalogueId;
  private String title;
  private List<String> cardIds;
  private String longUrl;
  @Setter
  private String shortUrl;
  private String creator;
}
