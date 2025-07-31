Feature: CT07 - Realizar preenchimento do formulário para Dropdown This app

  @CT07
  Scenario: CT07 - Preencher form components para This app is awesome
    Given que o usuario esteja na tela de Forms this app
    When preencher o Input field do formulario this app
    And Swtich como off this app
    And preencher o Dropdown como This app is awesome
    Then devo validar o botao active com OK para esse menu this app

