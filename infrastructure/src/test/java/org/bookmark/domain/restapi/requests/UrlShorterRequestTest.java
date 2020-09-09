package org.bookmark.domain.restapi.requests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import lombok.val;
import org.junit.Test;

public class UrlShorterRequestTest {

  @Test
  public void should_convert_to_command(){
    val request = new UrlShorterRequest("http://longUrl", LocalDate.now());

    val command = request.toCommand();

    assertThat(command.getLongUrl()).isEqualTo("http://longUrl");
    assertThat(command.getExpiryDate()).isEqualTo(LocalDate.now());
  }

}
