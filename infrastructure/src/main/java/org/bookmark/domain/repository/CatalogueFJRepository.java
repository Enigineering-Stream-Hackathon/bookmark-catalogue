package org.bookmark.domain.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.util.List;
import lombok.SneakyThrows;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.repositories.CatalogueRepository;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogueFJRepository implements CatalogueRepository {

  @Autowired
  private FluentJdbc fluentJdbc;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @SneakyThrows
  public void save(Catalogue catalogue) {
    fluentJdbc.query()
        .update("insert into T_CATALOGUE values(?, ?, ?, ?, ?, ?)")
        .params(
            catalogue.getCatalogueId(),
            catalogue.getTitle(),
            objectMapper.writeValueAsString(catalogue.getCardIds()),
            catalogue.getLongUrl(),
            catalogue.getShortUrl(),
            catalogue.getCreator())
        .run();
  }

  @Override
  public List<Catalogue> findAll() {
    return fluentJdbc.query().select("select * from T_CATALOGUE")
        .listResult(this::buildCatalogue);

  }

  @Override
  public Catalogue findByCatalogueId(String catalogueId) {
    return fluentJdbc.query().select("select * from T_CATALOGUE where ID = ?")
        .params(catalogueId)
        .firstResult(this::buildCatalogue)
        .orElse(null);
  }

  @Override
  public String getLongUrl(String shortUrl) {
    return fluentJdbc.query().select("select LONG_URL from T_CATALOGUE where SHORT_URL = ?")
        .params(shortUrl)
        .firstResult(Mappers.singleString())
        .orElse(null);
  }

  @SneakyThrows
  private Catalogue buildCatalogue(ResultSet it) {
    return new Catalogue(
        it.getString("ID"),
        it.getString("TITLE"),
        objectMapper.readValue(it.getString("CARDS"), new TypeReference<List<String>>() {
        }),
        it.getString("LONG_URL"),
        it.getString("SHORT_URL"),
        it.getString("CREATOR"));
  }
}
