package org.bookmark.domain;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import lombok.val;
import org.bookmark.domain.commands.UrlCommand;
import org.bookmark.domain.enitites.UrlEntity;
import org.bookmark.domain.exception.UrlExpiredException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

  private final static String LONG_URL = "http://chart.apis.google.com/chart?chs=500x500&chma=0,0,100,100&cht=p&chco=FF0000%2CFFFF00%7CFF8000%2C00FF00%7C00FF00%2C0000FF&chd=t%3A122%2C42%2C17%2C10%2C8%2C7%2C7%2C7%2C7%2C6%2C6%2C6%2C6%2C5%2C5&chl=122%7C42%7C17%7C10%7C8%7C7%7C7%7C7%7C7%7C6%7C6%7C6%7C6%7C5%7C5&chdl=android%7Cjava%7Cstack-trace%7Cbroadcastreceiver%7Candroid-ndk%7Cuser-agent%7Candroid-webview%7Cwebview%7Cbackground%7Cmultithreading%7Candroid-source%7Csms%7Cadb%7Csollections%7Cactivity|Chart";

  @Mock
  private UrlRepository repository;

  @InjectMocks
  private UrlService service;

  @Test
  public void should_call_repository_to_save_entity() {
    val argumentCaptor = ArgumentCaptor.forClass(UrlEntity.class);
    val command = new UrlCommand(LONG_URL, LocalDate.now().plus(10, DAYS));

    val urlId = service.createShortUrl(command);

    assertThat(urlId).isNotNull();
    verify(repository).save(argumentCaptor.capture());
    val entity = argumentCaptor.getValue();
    assertThat(entity.getLongUrl()).isEqualTo(LONG_URL);
    assertThat(entity.getExpiryDate()).isEqualTo(LocalDate.now().plus(10, DAYS));
    assertThat(entity.getCreationDate()).isEqualTo(LocalDate.now());

  }

  @Test
  public void should_return_long_url_when_shortUrlId_is_provided_if_not_expired() {
    when(repository.get("asdadad")).thenReturn(
        new UrlEntity("asdadad", LONG_URL, LocalDate.now().plus(10, DAYS),
            LocalDate.now().minus(10, DAYS)));

    val longUrl = service.getLongUrl("asdadad");

    assertThat(longUrl).isEqualTo(LONG_URL);

  }

  @Test

  public void should_throw_exception_when_shortUrlId_is_provided_but_already_expired() {
    when(repository.get("asdadad")).thenReturn(
        new UrlEntity("asdadad", LONG_URL, LocalDate.now().minus(2, DAYS),
            LocalDate.now().minus(10, DAYS)));

    assertThatCode(() -> service.getLongUrl("asdadad"))
        .isExactlyInstanceOf(UrlExpiredException.class);
  }

}
