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
import lombok.val;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.queries.CatalogueQuery;
import org.bookmark.domain.restapi.requests.CreateCatalogueRequest;
import org.bookmark.domain.services.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Catalogue Controller")
public class CatalogueController {

  @Autowired
  private CatalogueService catalogueService;

  @Operation(summary = "Create catalogue with a specific category and subcategory")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Catalogue created successfully",
          content = { @Content(mediaType = "application/json",
              schema = @Schema()) }),
      @ApiResponse(responseCode = "500", description = "Server error",
          content = @Content) })
  @PostMapping(value = "/catalogue", consumes = "application/json")
  public ResponseEntity<Void> create(@RequestBody CreateCatalogueRequest request) {
    catalogueService.create(request.toCardCommand());
    return status(CREATED).build();
  }

  @Operation(summary = "Get all catalogues")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catalogues retrieved successfully",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Catalogue.class))})
  })
  @GetMapping(value = "/catalogues")
  public ResponseEntity<List<Catalogue>> getAllCatalogues(){
    val catalogues = catalogueService.findAllCatalogues();
    return ResponseEntity.status(OK).body(catalogues);
  }

  @Operation(summary = "Get catalogue details for the catalogue Id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catalogue retrieved successfully",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Catalogue.class))}),
      @ApiResponse(responseCode = "404", description = "Catalogue not found",
          content = @Content) })
  @GetMapping(value = "/catalogue")
  public ResponseEntity<CatalogueQuery> findCatalogue(@RequestParam(value = "catalogueId") String catalogueId){
    val catalogue = catalogueService.findCatalogue(catalogueId);
    return ResponseEntity.status(OK).body(catalogue);
  }


}
