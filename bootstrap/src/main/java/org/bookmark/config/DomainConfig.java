package org.bookmark.config;


import org.bookmark.domain.UrlService;
import org.bookmark.domain.repository.FluentJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

  private final FluentJdbcRepository repository;

  public DomainConfig(FluentJdbcRepository repository) {
    this.repository = repository;
  }

  @Bean
  public UrlService urlService() {
    return new UrlService(repository);
  }
}
