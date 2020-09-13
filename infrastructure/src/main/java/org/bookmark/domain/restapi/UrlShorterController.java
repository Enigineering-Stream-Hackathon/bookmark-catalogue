package org.bookmark.domain.restapi;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.bookmark.domain.UrlService;
import org.bookmark.domain.restapi.requests.UrlShorterRequest;
import org.bookmark.domain.restapi.responses.ShortUrlResponse;
import org.bookmark.domain.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UrlShorterController {

  @Autowired
  private UrlService service;

  @Autowired
  private CardService cardService;

  @PostMapping(value = "/short-url", consumes = "application/json")
  public ResponseEntity<ShortUrlResponse> create(@RequestBody UrlShorterRequest request) {
    val shortUrl = "http://localhost:8080/tiny/quicky/"
        .concat(service.createShortUrl(request.toCommand()));
    return status(CREATED).body(new ShortUrlResponse(shortUrl));
  }

  @GetMapping(value = "/tiny/{context}/{url}")
  public ResponseEntity getAndRedirect(@PathVariable String context, @PathVariable String url) {
    log.info("Request received to redirect for url {} and context {}", url, context);
    val longUrl = "quicky".equals(context) ? service.getLongUrl(url)
        : cardService.getCardLongUrl(context, url);
    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(longUrl))
        .build();
  }
}
