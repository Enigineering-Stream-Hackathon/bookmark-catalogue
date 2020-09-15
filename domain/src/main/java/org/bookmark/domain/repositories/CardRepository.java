package org.bookmark.domain.repositories;

import java.util.List;
import org.bookmark.domain.commands.Category;
import org.bookmark.domain.enitites.Card;

public interface CardRepository {

  void save(Card card);

  List<Card> getByContext(String context);

  List<Card> findByCategoryAndSubCategory(Category category, String subCategory);

  Card findById(String cardId);
}
