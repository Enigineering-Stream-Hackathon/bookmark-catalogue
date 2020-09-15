package org.bookmark.domain.features.stubs;

import java.util.List;
import lombok.AllArgsConstructor;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.repositories.CatalogueRepository;

@AllArgsConstructor
public class InMemoryCatalogueRepositoryStub implements CatalogueRepository {

  private final TestContext testContext;

  @Override
  public void save(Catalogue catalogue) {
    testContext.addCatalogue(catalogue);
  }

  @Override
  public List<Catalogue> findAll() {
    return testContext.getCatalogues();
  }

  @Override
  public Catalogue findByCatalogueId(String catalogueId) {
    return testContext.getCatalogues().stream()
        .filter(it -> it.getCatalogueId().equals(catalogueId))
        .findFirst()
        .orElse(null);
  }

  @Override
  public String getLongUrl(String shortUrl) {
    return testContext.getCatalogues().stream()
        .filter(it -> it.getShortUrl().equals(shortUrl))
        .findFirst()
        .map(Catalogue::getLongUrl)
        .orElse(null);
  }
}
