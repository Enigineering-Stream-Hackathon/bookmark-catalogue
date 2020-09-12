package org.bookmark.domain.repository;

import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.repositories.CardRepository;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CardFJRepository implements CardRepository {

  @Autowired
  private FluentJdbc fluentJdbc;

  @Override
  public void save(Card card) {
    fluentJdbc.query()
        .update("insert into T_CARD values(?, ?, ?, ?, ?, ?, ?)")
        .params(card.getCardId(),
            card.getTitle(),
            card.getDescription(),
            card.getLongUrl(),
            card.getShortUrl(),
            card.getCreator(),
            card.getCreatedOn())
        .run();
  }
}
