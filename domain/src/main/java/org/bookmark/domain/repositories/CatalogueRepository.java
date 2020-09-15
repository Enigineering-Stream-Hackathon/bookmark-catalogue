package org.bookmark.domain.repositories;

import java.util.List;
import org.bookmark.domain.enitites.Catalogue;

public interface CatalogueRepository {

  void save(Catalogue catalogue);

  List<Catalogue> findAll();

  Catalogue findByCatalogueId(String catalogueId);

  String getLongUrl(String shortUrl);
}
