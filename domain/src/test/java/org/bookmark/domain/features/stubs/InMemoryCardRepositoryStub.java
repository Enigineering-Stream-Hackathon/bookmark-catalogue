package org.bookmark.domain.features.stubs;

import java.util.List;
import java.util.stream.Collectors;
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

  @Override
  public List<Card> getByContext(String context) {
    return testContext.getCards().stream()
        .filter(it -> it.getContext().equals(context))
        .collect(Collectors.toList());
  }
}
