package org.bookmark.domain.services;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;
import lombok.val;
import org.bookmark.domain.commands.CatalogueCommand;
import org.bookmark.domain.commands.Category;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.repositories.CardRepository;
import org.bookmark.domain.repositories.CatalogueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogueServiceTest {

  @Mock
  private CardRepository cardRepository;

  @Mock
  private CatalogueRepository catalogueRepository;

  @InjectMocks
  private CatalogueService service;


  @Test
  public void should_create_a_new_catalogue_when_create_is_invoked() {
    val argumentCaptor = ArgumentCaptor.forClass(Catalogue.class);
    val command = new CatalogueCommand("Catalogue Title", Category.FEATURE_TEAM, "FT1",
        "http://longurl", "black.panther");

    service.create(command);

    verify(cardRepository)
        .findByCategoryAndSubCategory(command.getCategory(), command.getSubCategory());
    verify(catalogueRepository).save(argumentCaptor.capture());
    val entity = argumentCaptor.getValue();
    assertThat(entity.getCreator()).isEqualTo("black.panther");
    assertThat(entity.getTitle()).isEqualTo(command.getTitle());
    assertThat(entity.getLongUrl()).isEqualTo(command.getLongUrl().concat(entity.getCatalogueId()));


  }

  @Test
  public void should_get_all_catalogues_when_invoked() {
    service.findAllCatalogues();
    verify(catalogueRepository).findAll();
  }

  @Test
  public void should_find_catalogue_when_invoked_with_catalogueId() {
    val catalogueId = UUID.randomUUID().toString();

    when(catalogueRepository.findByCatalogueId(catalogueId)).thenReturn(
        new Catalogue(catalogueId, "Test cata", asList("12341", "34335253"), "http://longurl",
            "tribe/shortUrl",
            "ironMan"));

    val result = service.findCatalogue(catalogueId);
    verify(catalogueRepository).findByCatalogueId(catalogueId);
    verify(cardRepository, times(2)).findById(anyString());
  }


  @Test
  public void should_get_catalogue_long_url_when_short_url_is_passed() {
    service.getCatalogueLongUrl("tribe1", "adsashdhalkdnslkfalkla");
    verify(catalogueRepository).getLongUrl("tribe1/adsashdhalkdnslkfalkla");
  }

}
