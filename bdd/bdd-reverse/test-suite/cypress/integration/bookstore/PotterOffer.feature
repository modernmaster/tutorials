Feature: Harry Potter Offer Bonanza

  As a Bookstore
  I want  customers to buy the complete set of Harry Potter books
  So that they can enjoy the adventures of Harry

  Scenario: buy one book
    Given a customer
    When I buy any book
    Then It will cost me 8 EUR

  Scenario: buy two books
    Given a customer
    When I buy two books
    Then It will cost me 15.20 EUR

  Scenario: buy three books
    Given a customer
    When I buy three books
    Then It will cost me 21.60 EUR

  Scenario: buy four books
    Given a customer
    When I buy four books
    Then It will cost me 25.60 EUR

  Scenario: buy five books
    Given a customer
    When I buy five books
    Then It will cost me 32 EUR
