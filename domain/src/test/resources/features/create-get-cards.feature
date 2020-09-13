Feature: As an User, I want to create new cards and see the cards created by me

  Scenario: Create a card for a long url
    Given Sabu wants to create a card with title CARD_TITLE description TEST for long url https://longurl.com with context Chris
    When Sabu clicks on create card
    Then a card should be created for CARD_TITLE for Sabu