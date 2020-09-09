package org.bookmark.domain.features.steps;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.time.LocalDate;
import lombok.SneakyThrows;
import org.bookmark.domain.UrlService;
import org.bookmark.domain.enitites.UrlEntity;
import org.bookmark.domain.exception.UrlExpiredException;
import org.bookmark.domain.features.stubs.TestContext;

public class GetShortUrlStepDef {

  private TestContext testContext;
  private UrlService service;

  public GetShortUrlStepDef(TestContext testContext, UrlService service) {
    this.testContext = testContext;
    this.service = service;
  }

  private String shortUrl;
  private String longUrl;

  @Before
  public void setUp() {
    testContext.init();
  }

  @Given("A user wants to redirect to actual website when the short url is \"([^\"]*)\"")
  public void given(String shortUrl) {
    this.shortUrl = shortUrl;
    testContext
        .addUrlEntity(new UrlEntity(shortUrl, "https://longurl", LocalDate.now().plus(20, DAYS), LocalDate.now()));
  }

  @When("the user search in the browser with the short url")
  public void when() {
    longUrl = service.getLongUrl(shortUrl);
  }

  @Then("user shall get the actual long url \"([^\"]*)\"")
  public void then(String expected) {
    assertThat(longUrl).isEqualTo(expected);
  }

  @Given("A user wants to redirect to actual website when the short url is \"([^\"]*)\" which already expired")
  public void given_scenario(String shortUrl) {
    this.shortUrl = shortUrl;
    testContext
        .addUrlEntity(new UrlEntity(shortUrl, "https://longurl", LocalDate.now().minus(20, DAYS), LocalDate.now()));
  }

  @Then("user shall get an exception")
  public void then_scenario() {
    assertThatCode(() -> service.getLongUrl(shortUrl)).isExactlyInstanceOf(UrlExpiredException.class);
  }

  @After
  public void after() {
    testContext.clearContext();
  }
}
