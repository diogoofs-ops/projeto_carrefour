# 📱 Projeto de Automação Mobile Android

Este projeto tem como objetivo validar automaçã Mobile por meio de testes automatizados utilizando **Java**, **Cucumber** e **Appium**. Os testes abrangem testes positivos e negativos.

## 📁 Estrutura dos Testes

Este projeto conta com uma suíte de testes automatizados que valida as principais funcionalidades do aplicativo mobile, garantindo estabilidade e confiabilidade nas interações do usuário.

## ✅ Testes Implementados

- CT01 Login no aplicativo com sucesso
- CT02 Realizar Sign up para cadastro de usuário
- CT03 Navegar entre as telas do App
- CT04 Fazer um Get Started no Webview
- CT05 Preenchimento do formulário para Dropdown webdriver
- CT06 Preenchimento do formulário para Dropdown Appium
- CT07 Preenchimento do formulário para Dropdown This app
- CT08 Realizar Swipe dentro do app
- CT09 Acesso à funcionalidade de Drag dentro do app
- CT10 Validação da mensagem de erro ao tentar login com credenciais inválidas



## 🚀 Tecnologias
- Java
- Appium
- Cucumber
- Allure Reports
- Maven
- GitHub Actions

## 🧰 Como executar
1. Instale Appium e inicie o servidor
2. Configure o APK no `DriverFactory.java`
3. Execute os testes com:
   ```bash
   mvn clean test