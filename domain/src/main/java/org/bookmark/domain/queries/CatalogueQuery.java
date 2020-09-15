package org.bookmark.domain.queries;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookmark.domain.enitites.Card;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueQuery {
  private String catalogueId;
  private String title;
  private List<Card> cards;
  private String longUrl;
  private String shortUrl;
  private String creator;
}
