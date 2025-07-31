# 🧪 Projeto de Testes Automatizados de API com RestAssured

Este projeto tem como objetivo validar a API pública https://serverest.dev por meio de testes automatizados utilizando **Java**, **JUnit 5** e **RestAssured**. Os testes abrangem operações de CRUD, autenticação, testes negativos e validações de performance.

---

## 📁 Estrutura dos Testes

### ✅ Testes Funcionais

- **CreateUserTests.java**  
  Cria um novo usuário com nome, email, senha e perfil de administrador.  
  ✔️ Espera-se o status `201 Created` e uma mensagem de sucesso no console.

- **GetUserByIdTest.java**  
  Busca o primeiro usuário da lista e realiza uma requisição GET por ID.  
  ✔️ Valida se o ID retornado corresponde ao esperado.

- **UpdateUserTests.java**  
  Atualiza os dados de um usuário existente com novos valores.  
  ✔️ Verifica se o status da resposta é `200 OK` ou `400 Bad Request`.

- **DeleteUserTest.java**  
  Cria um usuário e em seguida o exclui.  
  ✔️ Valida se a mensagem `"Registro excluído com sucesso"` é retornada.

- **UserTests.java**  
  Realiza uma requisição GET para listar todos os usuários.  
  ✔️ Verifica se o campo `usuarios` não é nulo e se o status é `200 OK`.

---

### ❌ Testes Negativos

- **NegativeTests.java**  
  Tenta realizar login com credenciais inválidas.  
  ✔️ Espera-se o status `400 Bad Request` e uma mensagem de falha no console.

---

### 🚀 Testes de Performance

- **LoadTest.java**  
  Envia mais de 100 requisições em seguida, para validar as limitações da API.  
  ✔️ Espera-se o status 200 para sucesso.

---

## 🛠️ Tecnologias Utilizadas

- Java 11+
- JUnit 5
- RestAssured
- Maven ou Gradle

---

## ▶️ Como Executar os Testes

### ✅ Pré-requisitos

- Java 11 ou superior instalado
- Maven ou Gradle instalado
- IDE como IntelliJ IDEA, Eclipse ou VS Code (opcional)
- Conexão com a internet (a API testada é pública)

---

### 🧪 Executando com Maven

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```
2. Rodar todos os Métodos de Teste:
    ```mvn test ```
