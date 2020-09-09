Feature: As an User, I want to redirect to actual long url when short url provided

  Scenario: Get actual url from the short url
    Given A user wants to redirect to actual website when the short url is "http://shorturl"
    When the user search in the browser with the short url
    Then user shall get the actual long url "https://longurl"

  Scenario: Get exception if the url already expired
    Given A user wants to redirect to actual website when the short url is "http://expiredurl" which already expired
    Then user shall get an exception