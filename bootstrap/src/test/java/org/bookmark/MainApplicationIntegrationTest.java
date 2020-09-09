package org.bookmark;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.bookmark.domain.repository.FluentJdbcRepository;
import org.bookmark.domain.restapi.UrlShorterController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTest
@RunWith(SpringRunner.class)
public class MainApplicationIntegrationTest {

  @Autowired
  private UrlShorterController urlShorterController;

  @Autowired
  private FluentJdbcRepository fluentJdbcRepository;

  @Test
  public void should_create_an_controller() {
    assertThat(urlShorterController).isNotNull();
  }

  @Test
  public void should_create_an_repository() {
    assertThat(fluentJdbcRepository).isNotNull();
  }
}
