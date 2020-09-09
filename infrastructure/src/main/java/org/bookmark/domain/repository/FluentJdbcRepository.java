package org.bookmark.domain.repository;

import org.bookmark.domain.UrlRepository;
import org.bookmark.domain.enitites.UrlEntity;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FluentJdbcRepository implements UrlRepository {

  @Autowired
  private FluentJdbc fluentJdbc;

  @Override
  public void save(UrlEntity entity) {
    fluentJdbc.query()
        .update("insert into T_URL values(?, ?, ?, ?)")
        .params(entity.getId(), entity.getLongUrl(), entity.getExpiryDate(),
            entity.getCreationDate())
        .run();
  }

  @Override
  public UrlEntity get(String shortUrl) {
    return fluentJdbc.query().select("select * from T_URL where ID = ?")
        .params(shortUrl)
        .firstResult(it -> new UrlEntity(
            it.getString("ID"),
            it.getString("LONG_URL"),
            it.getDate("EXPIRY_DATE").toLocalDate(),
            it.getDate("CREATION_DATE").toLocalDate()))
        .orElse(null);
  }
}
