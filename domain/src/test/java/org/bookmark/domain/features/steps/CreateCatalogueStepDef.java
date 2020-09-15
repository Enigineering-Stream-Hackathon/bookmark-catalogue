package org.bookmark.domain.features.steps;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bookmark.domain.commands.CatalogueCommand;
import org.bookmark.domain.commands.Category;
import org.bookmark.domain.enitites.Catalogue;
import org.bookmark.domain.features.stubs.TestContext;
import org.bookmark.domain.queries.CatalogueQuery;
import org.bookmark.domain.services.CatalogueService;

@RequiredArgsConstructor
public class CreateCatalogueStepDef {

  private final TestContext testContext;
  private final CatalogueService service;


  private CatalogueCommand command;

  private List<Catalogue> catalogueList;

  private CatalogueQuery catalogueQuery;

  private String catalogueId;



  @Before
  public void setUp() {
    testContext.init();
  }

  @Given("([^\"]*) wants to create a catalogue of all the cards of ([^\"]*) of ([^\"]*) of title ([^\"]*)")
  public void given(String userName, String catagory, String subCatagory, String title) {
    command = new CatalogueCommand(title, Category.valueOf(catagory), subCatagory,
        "http://longurl", userName);
  }

  @When("Prince clicks on create catalogue")
  public void when() {
    service.create(command);
  }

  @Then("a catalogue should be created with ([^\"]*)")
  public void then(String title) {
    val actual = testContext.getCatalogues().stream().filter(it -> it.getTitle().equals(title))
        .findFirst().get();
    assertThat(actual.getTitle()).isEqualTo(title);
  }

  @Given("Jim came to the website and wanted to view all catalogues")
  public void given() {
    val command = new CatalogueCommand("FT1_Catalogue", Category.FEATURE_TEAM, "ft1",
        "http://longurl", "Abc");
    val command2 = new CatalogueCommand("FT2_Catalogue", Category.FEATURE_TEAM, "ft1",
        "http://longurl", "Abc");

    service.create(command);
    service.create(command2);
  }

  @When("Jim clicks on show all catalogues")
  public void when_viewed() {
    catalogueList = service.findAllCatalogues();
  }

  @Then("a list of catalogue of titles ([^\"]*) and ([^\"]*) should be displayed")
  public void then_display(String title1, String title2) {
    val titles = catalogueList.stream().map(Catalogue::getTitle).collect(toList());

    assertThat(titles.contains(title1)).isTrue();
    assertThat(titles.contains(title2)).isTrue();
  }


  @Given("Jim came to the website and wanted to view details of one catalogue")
  public void given_for_one_catalogue() {
    val command = new CatalogueCommand("FT3_Catalogue", Category.FEATURE_TEAM, "ft1",
        "http://longurl", "Abc");

    service.create(command);
    catalogueId = testContext.getCatalogues().stream()
        .filter(it -> it.getTitle().equals("FT3_Catalogue"))
        .findFirst()
        .map(Catalogue::getCatalogueId)
        .orElse(null);
  }

  @When("Jim clicks on to show details of a catalogue")
  public void when_viewed_for_one() {
    catalogueQuery = service.findCatalogue(catalogueId);
  }

  @Then("a catalogue should be retrieved of title ([^\"]*)")
  public void then_display_details(String title) {
    assertThat(catalogueQuery.getTitle()).isEqualTo(title);
  }

  @After
  public void after() {
    testContext.clearContext();
  }
}
