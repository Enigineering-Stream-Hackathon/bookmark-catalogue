package org.bookmark.domain.restapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import lombok.val;
import org.bookmark.domain.UrlService;
import org.bookmark.domain.commands.UrlCommand;
import org.bookmark.domain.restapi.requests.UrlShorterRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UrlShorterControllerTest {

  @Mock
  private UrlService service;

  @InjectMocks
  private UrlShorterController controller;

  private final static String LONG_URL = "http://chart.apis.google.com/chart?chs=500x500&chma=0,0,100,100&cht=p&chco=FF0000%2CFFFF00%7CFF8000%2C00FF00%7C00FF00%2C0000FF&chd=t%3A122%2C42%2C17%2C10%2C8%2C7%2C7%2C7%2C7%2C6%2C6%2C6%2C6%2C5%2C5&chl=122%7C42%7C17%7C10%7C8%7C7%7C7%7C7%7C7%7C6%7C6%7C6%7C6%7C5%7C5&chdl=android%7Cjava%7Cstack-trace%7Cbroadcastreceiver%7Candroid-ndk%7Cuser-agent%7Candroid-webview%7Cwebview%7Cbackground%7Cmultithreading%7Candroid-source%7Csms%7Cadb%7Csollections%7Cactivity|Chart";

  @Test
  public void should_call_service_to_create_short_url(){
    val argumentCaptor = ArgumentCaptor.forClass(UrlCommand.class);
    val request = new UrlShorterRequest(LONG_URL, LocalDate.now());
    when(service.createShortUrl(any())).thenReturn("1231sasadasdafsdafsadfsdaf");
    controller.create(request);

    verify(service).createShortUrl(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(
        new UrlCommand(LONG_URL, LocalDate.now()));

  }
}
