package org.bookmark.domain.restapi;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.bookmark.domain.UrlService;
import org.bookmark.domain.restapi.requests.UrlShorterRequest;
import org.bookmark.domain.restapi.responses.ShortUrlResponse;
import org.bookmark.domain.services.CardService;
import org.bookmark.domain.services.CatalogueService;
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
@Tag(name = "Url Shorter Controller")
public class UrlShorterController {

  @Autowired
  private UrlService service;

  @Autowired
  private CardService cardService;

  @Autowired
  private CatalogueService catalogueService;

  @Operation(summary = "Generate a quick shot url with a expiry date")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Short url generated successfully",
          content = { @Content(mediaType = "application/json",
              schema = @Schema()) }),
      @ApiResponse(responseCode = "500", description = "Server error",
          content = @Content) })
  @PostMapping(value = "/short-url", consumes = "application/json")
  public ResponseEntity<ShortUrlResponse> create(@RequestBody UrlShorterRequest request) {
    val shortUrl = "http://localhost:8080/tiny/quicky/"
        .concat(service.createShortUrl(request.toCommand()));
    return status(CREATED).body(new ShortUrlResponse(shortUrl));
  }

  //TODO refactor this method
  @Operation(summary = "Redirect to the actual url when short url provided")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "Rerouted to the actual Url"),
      @ApiResponse(responseCode = "404", description = "Url not found",
          content = @Content) })
  @GetMapping(value = "/tiny/{context}/{url}")
  public ResponseEntity getAndRedirect(@PathVariable String context, @PathVariable String url) {
    log.info("Request received to redirect for url {} and context {}", url, context);
    String longUrl = "quicky".equals(context) ? service.getLongUrl(url)
        : cardService.getCardLongUrl(context, url);
    if(isNull(longUrl)){
      longUrl = catalogueService.getCatalogueLongUrl(context, url);
    }
    if(isNull(longUrl)) {
      return ResponseEntity.status(NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(longUrl))
        .build();
  }
}
