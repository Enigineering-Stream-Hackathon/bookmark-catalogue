package org.bookmark.domain.features.stubs;

import lombok.AllArgsConstructor;
import org.bookmark.domain.UrlRepository;
import org.bookmark.domain.enitites.UrlEntity;

@AllArgsConstructor
public class InMemoryRepositoryStub implements UrlRepository {

  private final TestContext testContext;

  @Override
  public void save(UrlEntity entity) {
    testContext.addUrlEntity(entity);
  }

  @Override
  public UrlEntity get(String shortUrl) {
    return testContext.getUrlEntities().stream()
        .filter(it -> it.getId().equals(shortUrl))
        .findFirst().get();
  }
}
