package org.bookmark.domain.features.steps;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bookmark.domain.commands.CardCommand;
import org.bookmark.domain.features.stubs.TestContext;
import org.bookmark.domain.services.CardService;

@RequiredArgsConstructor
public class CreateCardStepDef {
  private final TestContext testContext;
  private final CardService service;


  private CardCommand command;

  @Before
  public void setUp() {
    testContext.init();
  }

  @Given("([^\"]*) wants to create a card with title ([^\"]*) description ([^\"]*) for long url ([^\"]*)")
  public void given(String userName, String title, String description, String longUrl) {
    command = new CardCommand(title, description, longUrl, userName);
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

  @After
  public void after() {
    testContext.clearContext();
  }
}
