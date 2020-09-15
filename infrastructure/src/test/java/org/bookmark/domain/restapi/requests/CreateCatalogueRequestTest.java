package org.bookmark.domain.restapi.requests;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.bookmark.domain.commands.CatalogueCommand;
import org.bookmark.domain.commands.Category;
import org.junit.Test;

public class CreateCatalogueRequestTest {

  @Test
  public void should_convert_to_command() {
    val request = new CreateCatalogueRequest("Catalogue Title", Category.FEATURE_TEAM, "FT1",
        "http://longurl", "black.panther");
    val command = request.toCardCommand();

    assertThat(command).isEqualToComparingFieldByField(
        new CatalogueCommand("Catalogue Title", Category.FEATURE_TEAM, "FT1",
            "http://longurl", "black.panther"));
  }
}
