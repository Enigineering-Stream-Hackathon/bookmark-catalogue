package org.bookmark.domain.services;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.val;
import org.bookmark.domain.commands.CatalogueCommand;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.queries.CatalogueQuery;
import org.bookmark.domain.repositories.CardRepository;
import org.bookmark.domain.repositories.CatalogueRepository;

@AllArgsConstructor
public class CatalogueService {

  private final CardRepository cardRepository;

  private final CatalogueRepository catalogueRepository;

  public void create(CatalogueCommand command) {
    val cards = cardRepository
        .findByCategoryAndSubCategory(command.getCategory(), command.getSubCategory())
        .stream()
        .map(Card::getCardId).collect(toList());
    val catalogueId = UUID.randomUUID().toString();
    val longUrl = command.getLongUrl().concat(catalogueId);
    val shortUrl = new GenerateShortUrl()
        .getShortUrl(longUrl, command.getSubCategory());
    val catalogue = new Catalogue(
        catalogueId,
        command.getTitle(),
        cards,
        longUrl,
        shortUrl,
        command.getCreator()
    );

    catalogueRepository.save(catalogue);


  }

  public List<Catalogue> findAllCatalogues() {
    val catalogs =  catalogueRepository.findAll();
    catalogs.forEach(it -> it.setShortUrl("https://bookmark-catalogue.herokuapp.com/tiny/".concat(it.getShortUrl())));
    return catalogs;
  }

  public CatalogueQuery findCatalogue(String catalogueId) {
    val catalogue = catalogueRepository.findByCatalogueId(catalogueId);
    val cards = catalogue.getCardIds().stream()
        .map(cardRepository::findById)
        .collect(toList());
    cards.forEach(it -> it.setShortUrl("https://bookmark-catalogue.herokuapp.com/tiny/".concat(it.getShortUrl())));
    return new CatalogueQuery(catalogue.getCatalogueId(), catalogue.getTitle(), cards,
        catalogue.getLongUrl(), "https://bookmark-catalogue.herokuapp.com/tiny/".concat(catalogue.getShortUrl()), catalogue.getCreator());
  }

  public String getCatalogueLongUrl(String context, String url) {
    return catalogueRepository.getLongUrl(context.concat("/".concat(url)));
  }
}
