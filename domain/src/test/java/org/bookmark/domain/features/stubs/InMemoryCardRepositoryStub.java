package org.bookmark.domain.features.stubs;

import lombok.AllArgsConstructor;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.repositories.CardRepository;

@AllArgsConstructor
public class InMemoryCardRepositoryStub implements CardRepository {

  private final TestContext testContext;

  @Override
  public void save(Card card) {
    testContext.addCard(card);
  }
}
