Feature: CT05 - Realizar preenchimento do formulário para Dropdown webdriver

  @CT05
  Scenario: CT05 - Preencher form components para webdriver.io is awesome
    Given que o usuario esteja na tela de Forms webDriver
    When preencher o Input field do formulario webDriver
    And Swtich como off webDriver
    And preencher o Dropdown como webdriver.io is awesome
    Then devo validar o botao active com OK para esse menu webDriver

