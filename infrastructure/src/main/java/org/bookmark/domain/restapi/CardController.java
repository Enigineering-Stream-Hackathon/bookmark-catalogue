package org.bookmark.domain.restapi;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookmark.domain.restapi.requests.CreateCardRequest;
import org.bookmark.domain.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}
