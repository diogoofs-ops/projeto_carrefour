Feature: CT06 - Realizar preenchimento do formulário para Dropdown Appium
  @CT06
  Scenario: CT06 - Preencher form components para Appium is awesome
    Given que o usuario esteja na tela de Forms Appium
    When preencher o Input field do formulario Appium
    And Swtich como off Appium
    And preencher o Dropdown como Appium is awesome
    Then devo validar o botao active com OK para esse menu Appium

