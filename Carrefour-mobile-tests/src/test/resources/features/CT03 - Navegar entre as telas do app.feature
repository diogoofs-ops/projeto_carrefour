Feature: CT03 - Navegar entre as telas do App

  @CT03
  Scenario: CT03 - Navegar pelas telas do App
    Given que o usuario esteja na Home do app
    When passar pelas telas de Webview e Login
    And pelas telas de Forms e Swipe
    Then devo validar a navegacao pela tela de Drag
