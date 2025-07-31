Feature: CT10 - Validar mensagem de erro para login

  @CT10
  Scenario: CT10 - Mensagem de erro para login
    Given que o usuario incorreto esteja na tela de login
    When inserir email incorreto na tela de login
    Then devo validar mensagem de erro para login

