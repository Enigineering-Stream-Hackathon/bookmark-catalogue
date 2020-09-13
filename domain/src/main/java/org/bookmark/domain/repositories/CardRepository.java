package org.bookmark.domain.repositories;

import java.util.List;
import org.bookmark.domain.enitites.Card;

public interface CardRepository {

  void save(Card card);

  List<Card> getByContext(String context);

}
