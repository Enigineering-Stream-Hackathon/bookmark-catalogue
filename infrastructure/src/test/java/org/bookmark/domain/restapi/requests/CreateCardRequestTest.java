package org.bookmark.domain.restapi.requests;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.Test;

public class CreateCardRequestTest {

  @Test
  public void should_convert_to_command() {
    val request = new CreateCardRequest("TITLE", "DESC", "http://longurl",
        "ironman", "chris", "FT1", "Tribe1" , "PlatForm1");

    val command = request.toCardCommand();

    assertThat(command.getTitle()).isEqualTo("TITLE");
    assertThat(command.getDescription()).isEqualTo("DESC");
    assertThat(command.getLongUrl()).isEqualTo("http://longurl");
    assertThat(command.getCreator()).isEqualTo("ironman");
    assertThat(command.getContext()).isEqualTo("chris");
    assertThat(command.getFeatureTeam()).isEqualTo("FT1");
    assertThat(command.getTribe()).isEqualTo("Tribe1");
    assertThat(command.getPlatform()).isEqualTo("PlatForm1");
  }
}
