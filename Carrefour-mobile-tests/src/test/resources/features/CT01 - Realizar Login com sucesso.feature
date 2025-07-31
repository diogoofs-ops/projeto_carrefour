Feature: CT01 - Login no aplicativo com sucesso

  @CT01
  Scenario: CT01 - Login com credenciais válidas
    Given que o usuario esteja na tela de login
    When inserir email e password
    Then devera ser validado com sucesso