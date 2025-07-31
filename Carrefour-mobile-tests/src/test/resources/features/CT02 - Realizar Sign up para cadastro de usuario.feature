Feature: CT02 - Realizar Sign up para cadastro de usuario

  @CT02
  Scenario: CT02 - Cadastro de login com sucesso
    Given que o usuario esteja na tela de Sign up
    When inserir email e password e confirm password
    Then devo validar sign up com sucesso

