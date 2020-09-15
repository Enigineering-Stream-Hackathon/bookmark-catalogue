package org.bookmark.domain.features.stubs;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.bookmark.domain.commands.Category;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.repositories.CardRepository;

@AllArgsConstructor
public class InMemoryCardRepositoryStub implements CardRepository {

  private final TestContext testContext;

  @Override
  public void save(Card card) {
    testContext.addCard(card);
  }

  @Override
  public List<Card> getByContext(String context) {
    return testContext.getCards().stream()
        .filter(it -> it.getContext().equals(context))
        .collect(Collectors.toList());
  }

  @Override
  public List<Card> findByCategoryAndSubCategory(Category category, String subCategory) {
    return testContext.getCards().stream()
        .filter(it -> it.getTribe().equals(subCategory))
        .collect(Collectors.toList());
  }

  @Override
  public Card findById(String cardId) {
    return testContext.getCards().stream()
        .filter(it -> it.getCardId().equals(cardId))
        .findFirst()
        .orElse(null);
  }
}
