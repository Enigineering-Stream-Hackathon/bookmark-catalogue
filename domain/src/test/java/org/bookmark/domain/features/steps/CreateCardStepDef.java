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
import org.bookmark.domain.commands.CardCommand;
import org.bookmark.domain.enitites.Card;
import org.bookmark.domain.features.stubs.TestContext;
import org.bookmark.domain.services.CardService;

@RequiredArgsConstructor
public class CreateCardStepDef {

  private final TestContext testContext;
  private final CardService service;


  private CardCommand command;
  private List<Card> cards;

  @Before
  public void setUp() {
    testContext.init();
  }

  @Given("([^\"]*) wants to create a card with title ([^\"]*) description ([^\"]*) for long url ([^\"]*) with context ([^\"]*)")
  public void given(String userName, String title, String description, String longUrl,
      String context) {
    command = new CardCommand(title, description, longUrl, userName, context, "FT1", "Tribe1",
        "PlatForm1");
  }

  @When("Sabu clicks on create card")
  public void when() {
    service.create(command);
  }

  @Then("a card should be created for ([^\"]*) for ([^\"]*)")
  public void then(String title, String user) {
    val actual = testContext.getCards().stream().filter(it -> it.getCreator().equals(user))
        .findFirst().get();
    assertThat(actual.getTitle()).isEqualTo(title);
  }

  @Given("([^\"]*) wants to view all the cards created by him")
  public void given_for_cards(String context) {
    val command = new CardCommand("Title 1", "Description", "http://localhost", context, context,
        "FT1", "Tribe1", "PlatForm1");
    val command2 = new CardCommand("Title 2", "Description", "http://localhost", context, context,
        "FT1", "Tribe1", "PlatForm1");
    service.create(command);
    service.create(command2);
  }

  @When("([^\"]*) clicks on view cards")
  public void when_queried_for_all_cards(String context) {
    cards = service.getCardsByContext(context);
  }

  @Then("all the cards created should be displayed")
  public void then_display_all_the_cards() {
    val titles = cards.stream().map(Card::getTitle).collect(toList());

    assertThat(titles.contains("Title 1")).isTrue();
    assertThat(titles.contains("Title 2")).isTrue();
  }


  @After
  public void after() {
    testContext.clearContext();
  }
}
