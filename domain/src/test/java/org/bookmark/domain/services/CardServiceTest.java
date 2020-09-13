package org.bookmark.domain.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import lombok.val;
import org.bookmark.domain.commands.CardCommand;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.repositories.CardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

  private final static String LONG_URL = "http://chart.apis.google.com/chart?chs=500x500&chma=0,0,100,100&cht=p&chco=FF0000%2CFFFF00%7CFF8000%2C00FF00%7C00FF00%2C0000FF&chd=t%3A122%2C42%2C17%2C10%2C8%2C7%2C7%2C7%2C7%2C6%2C6%2C6%2C6%2C5%2C5&chl=122%7C42%7C17%7C10%7C8%7C7%7C7%7C7%7C7%7C6%7C6%7C6%7C6%7C5%7C5&chdl=android%7Cjava%7Cstack-trace%7Cbroadcastreceiver%7Candroid-ndk%7Cuser-agent%7Candroid-webview%7Cwebview%7Cbackground%7Cmultithreading%7Candroid-source%7Csms%7Cadb%7Csollections%7Cactivity|Chart";

  @Mock
  private CardRepository repository;

  @InjectMocks
  private CardService service;

  @Test
  public void should_call_repository_to_save_entity() {
    val argumentCaptor = ArgumentCaptor.forClass(Card.class);
    val command = new CardCommand("test_title", "test_des", LONG_URL, "admin", "chris", "FT1",
        "Tribe1", "PlatForm1");

    service.create(command);

    verify(repository).save(argumentCaptor.capture());
    val entity = argumentCaptor.getValue();
    assertThat(entity.getTitle()).isEqualTo("test_title");
    assertThat(entity.getDescription()).isEqualTo("test_des");
    assertThat(entity.getLongUrl()).isEqualTo(LONG_URL);
    assertThat(entity.getCreator()).isEqualTo("admin");
    assertThat(entity.getCreatedOn()).isEqualTo(LocalDate.now());
    assertThat(entity.getShortUrl()).isNotNull();
    assertThat(entity.getContext()).isEqualTo("chris");

  }

}
