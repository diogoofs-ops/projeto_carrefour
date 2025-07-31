Feature: CT09 - Realizar acesso aoa Drag dentro do app

  @CT09
  Scenario: CT09 - Realizar acesso ao Drag no app com sucesso
    Given que o usuario esteja na tela do Drag
    When fizer o Drag and Drop
    Then devo validar Drag com sucesso

