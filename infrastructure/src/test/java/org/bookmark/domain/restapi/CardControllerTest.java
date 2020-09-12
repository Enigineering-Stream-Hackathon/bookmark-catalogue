package org.bookmark.domain.restapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import lombok.val;
import org.bookmark.domain.commands.CardCommand;
import org.bookmark.domain.restapi.requests.CreateCardRequest;
import org.bookmark.domain.services.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardControllerTest {

  @Mock
  private CardService service;

  @InjectMocks
  private CardController controller;

  @Test
  public void should_call_service_to_create_short_url() {
    val argumentCaptor = ArgumentCaptor.forClass(CardCommand.class);
    val request = new CreateCardRequest("TITLE", "DESC", "http://longurl", "ironman");
    controller.create(request);

    verify(service).create(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(
        new CardCommand("TITLE", "DESC", "http://longurl", "ironman"));

  }
}
