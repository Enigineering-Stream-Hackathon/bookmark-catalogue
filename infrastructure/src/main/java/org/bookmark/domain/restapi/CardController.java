package org.bookmark.domain.restapi;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.restapi.requests.CreateCardRequest;
import org.bookmark.domain.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class CardController {

  private final CardService service;

  @PostMapping(value = "/card", consumes = "application/json")
  public ResponseEntity<Void> create(@RequestBody CreateCardRequest request) {
    service.create(request.toCardCommand());
    return status(CREATED).build();
  }

  @GetMapping(value = "/cards")
  public ResponseEntity<List<Card>> findByContext( @RequestParam(value = "context") String context) {
    return  status(OK).body(service.getCardsByContext(context));
  }
}
