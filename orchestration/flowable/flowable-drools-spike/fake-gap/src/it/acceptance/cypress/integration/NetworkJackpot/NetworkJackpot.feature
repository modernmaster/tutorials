Feature: Network Jackpot Single Instance
  I want to ensure that network jackpot is sending transaction to the correct gap installation

  Scenario: Verify wallet transactions are being passed to Network Jackpot
    Given I play a player with a deposit of 1000
    When I play a game
    Then I see WalletTransactions being passed to Network Jackpot

  Scenario: Verify jp transactions are for players from this instance
    Given I play a player with a deposit of 1000
    When I play a game
    Then I see NetworkJackpotTransactions being passed from Network Jackpot
      And contain a player id that is listed from this instance
