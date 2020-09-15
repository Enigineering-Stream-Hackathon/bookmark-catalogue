Feature: As an Admin, I want to create new catalogues and see the catalogues created by me

  Scenario: Create a catalogue for a category and subCategory
    Given Price wants to create a catalogue of all the cards of FEATURE_TEAM of ft1 of title FT1_Catalogue
    When Prince clicks on create catalogue
    Then a catalogue should be created with FT1_Catalogue


  Scenario: User wants to retrieve all the catalogues of the platform
    Given Jim came to the website and wanted to view all catalogues
    When Jim clicks on show all catalogues
    Then a list of catalogue of titles FT1_Catalogue and FT2_Catalogue should be displayed


  Scenario: User wants to retrieve details of a particular catalogue
    Given Jim came to the website and wanted to view details of one catalogue
    When Jim clicks on to show details of a catalogue
    Then a catalogue should be retrieved of title FT3_Catalogue