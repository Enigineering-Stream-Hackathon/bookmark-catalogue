package org.bookmark.domain.restapi.requests;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.Test;

public class CreateCardRequestTest {
  @Test
  public void should_convert_to_command(){
    val request = new CreateCardRequest("TITLE","DESC","http://longurl","ironman");

    val command = request.toCardCommand();

    assertThat(command.getTitle()).isEqualTo("TITLE");
    assertThat(command.getDescription()).isEqualTo("DESC");
    assertThat(command.getLongUrl()).isEqualTo("http://longurl");
    assertThat(command.getCreator()).isEqualTo("ironman");
  }
}
