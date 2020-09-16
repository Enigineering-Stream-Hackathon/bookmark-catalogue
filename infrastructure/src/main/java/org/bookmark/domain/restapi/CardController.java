package org.bookmark.domain.restapi;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Card Controller")
public class CardController {

  private final CardService service;

  @Operation(summary = "Create card with details")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Card created successfully",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Void.class)) }),
       })
  @PostMapping(value = "/card", consumes = "application/json")
  public ResponseEntity<Void> create(@RequestBody CreateCardRequest request) {
    service.create(request.toCardCommand());
    return status(CREATED).build();
  }

  @Operation(summary = "Get all cards for the context")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cards retrieved successfully",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Card.class))})})
  @GetMapping(value = "/cards")
  public ResponseEntity<List<Card>> findByContext( @RequestParam(value = "context") String context) {
    return  status(OK).body(service.getCardsByContext(context));
  }
}
