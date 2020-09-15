package org.bookmark.domain.restapi;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

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
public class CatalogueController {

  @Autowired
  private CatalogueService catalogueService;

  @PostMapping(value = "/catalogue", consumes = "application/json")
  public ResponseEntity<Void> create(@RequestBody CreateCatalogueRequest request) {
    catalogueService.create(request.toCardCommand());
    return status(CREATED).build();
  }

  @GetMapping(value = "/catalogues")
  public ResponseEntity<List<Catalogue>> getAllCatalogues(){
    val catalogues = catalogueService.findAllCatalogues();
    return ResponseEntity.status(OK).body(catalogues);
  }

  @GetMapping(value = "/catalogue")
  public ResponseEntity<CatalogueQuery> findCatalogue(@RequestParam(value = "catalogueId") String catalogueId){
    val catalogue = catalogueService.findCatalogue(catalogueId);
    return ResponseEntity.status(OK).body(catalogue);
  }


}
