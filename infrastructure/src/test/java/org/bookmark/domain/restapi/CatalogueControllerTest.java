package org.bookmark.domain.restapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import lombok.val;
import org.bookmark.domain.commands.CatalogueCommand;
import org.bookmark.domain.commands.Category;
import org.bookmark.domain.restapi.requests.CreateCatalogueRequest;
import org.bookmark.domain.services.CatalogueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogueControllerTest {

  @Mock
  private CatalogueService catalogueService;

  @InjectMocks
  private CatalogueController controller;

  @Test
  public void should_call_service_with_command_to_create() {
    val argumentCaptor = ArgumentCaptor.forClass(CatalogueCommand.class);
    val request = new CreateCatalogueRequest("Catalogue Title", Category.FEATURE_TEAM, "FT1",
        "http://longurl", "black.panther");

    controller.create(request);

    verify(catalogueService).create(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(
        new CatalogueCommand("Catalogue Title", Category.FEATURE_TEAM, "FT1",
            "http://longurl", "black.panther"));
  }


  @Test
  public void should_invoke_service_to_find_all_catalogues() {

    controller.getAllCatalogues();

    verify(catalogueService).findAllCatalogues();
  }

  @Test
  public void should_invoke_service_to_get_catalogue_for_catalogueId() {

    val catalogueId = UUID.randomUUID().toString();
    controller.findCatalogue(catalogueId);

    verify(catalogueService).findCatalogue(catalogueId);
  }

}
