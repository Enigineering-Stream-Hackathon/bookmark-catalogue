package org.bookmark.domain.repository;

import java.sql.ResultSet;
import java.util.List;
import lombok.SneakyThrows;
import org.bookmark.domain.commands.Category;
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
        .update("insert into T_CARD values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
        .params(card.getCardId(),
            card.getTitle(),
            card.getDescription(),
            card.getLongUrl(),
            card.getShortUrl(),
            card.getCreator(),
            card.getCreatedOn(),
            card.getContext(),
            card.getFeatureTeam(),
            card.getTribe(),
            card.getPlatform())
        .run();
  }

  @Override
  public List<Card> getByContext(String context) {
    return fluentJdbc.query().select("select * from T_CARD where CONTEXT = ?")
        .params(context)
        .listResult(this::buildCard);

  }

  @Override
  public List<Card> findByCategoryAndSubCategory(Category category, String subCategory) {
    return fluentJdbc.query().select("select * from T_CARD where " + category.name() + " = ?")
        .params(subCategory)
        .listResult(this::buildCard);
  }

  @SneakyThrows
  private Card buildCard(ResultSet it) {
    return new Card(it.getString("ID"),
        it.getString("TITLE"),
        it.getString("DESCRIPTION"),
        it.getString("LONG_URL"),
        it.getString("SHORT_URL"),
        it.getString("CREATOR"),
        it.getDate("CREATED_ON").toLocalDate(),
        it.getString("CONTEXT"),
        it.getString("FEATURE_TEAM"),
        it.getString("TRIBE"),
        it.getString("PLATFORM"));
  }

  @Override
  public Card findById(String cardId) {
    return fluentJdbc.query().select("select * from T_CARD where ID = ?")
        .params(cardId)
        .firstResult(this::buildCard).orElse(null);
  }
}
