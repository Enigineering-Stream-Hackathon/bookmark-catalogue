package org.bookmark.domain.features.steps;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.time.LocalDate;
import lombok.val;
import org.bookmark.domain.UrlService;
import org.bookmark.domain.commands.UrlCommand;
import org.bookmark.domain.features.stubs.TestContext;

public class CreateShortUrlStepDef {

  private TestContext testContext;
  private UrlService service;

  public CreateShortUrlStepDef(TestContext testContext, UrlService service) {
    this.testContext = testContext;
    this.service = service;
  }

  private UrlCommand command;
  private String shortUrl;

  @Before
  public void setUp() {
    testContext.init();
  }

  @Given("an user wants to create a short url for \"([^\"]*)\" which expires on ([^\"]*)")
  public void given(String longUrl, String expiryDate) {
    command = new UrlCommand(longUrl, LocalDate.parse(expiryDate));
  }

  @When("the user clicks on generate short url")
  public void when() {
    shortUrl = service.createShortUrl(command);
  }

  @Then("user shall get the short url")
  public void then() {
    val actual = testContext.getUrlEntities().stream().filter(it -> it.getId().equals(shortUrl))
        .findFirst().get();
    assertThat(actual.getId()).isEqualTo(shortUrl);
    assertThat(actual.getExpiryDate()).isEqualTo(LocalDate.of(2020, 10, 10));
  }

  @After
  public void after() {
    testContext.clearContext();
  }

}
