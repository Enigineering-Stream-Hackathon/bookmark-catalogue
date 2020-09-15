package org.bookmark.config;


import lombok.AllArgsConstructor;
import org.bookmark.domain.UrlService;
import org.bookmark.domain.repository.CardFJRepository;
import org.bookmark.domain.repository.CatalogueFJRepository;
import org.bookmark.domain.repository.FluentJdbcRepository;
import org.bookmark.domain.services.CardService;
import org.bookmark.domain.services.CatalogueService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DomainConfig {

  private final FluentJdbcRepository repository;

  private final CardFJRepository cardRepository;

  private final CatalogueFJRepository catalogueRepository;

  @Bean
  public UrlService urlService() {
    return new UrlService(repository);
  }

  @Bean
  public CardService cardService(){
    return new CardService(cardRepository);
  }

  @Bean
  public CatalogueService catalogueService() {
    return new CatalogueService(cardRepository, catalogueRepository);
  }
}
