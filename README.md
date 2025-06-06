
# ☔ Alaga Menos - Projeto Java (Gestão de Alertas de Alagamento)

### 🧑‍🤝‍🧑 Integrantes do Projeto

- **Gustavo de Aguiar Lima Silva** - RM: 557707  
- **Julio Cesar Conceição Rodrigues** - RM: 557298  
- **Matheus de Freitas Silva** - RM: 552602  

---

### 💡 Descrição da Solução

O projeto **Alaga Menos** é uma aplicação Java baseada em **Spring Boot**, estruturada com **Maven** para gerenciamento de dependências.
A solução foi criada com o objetivo de fornecer uma API robusta para o **gerenciamento de alertas de alagamentos urbanos**, permitindo o controle e consulta de entidades como Estado, Cidade, Bairro, Rua, Endereço, Alerta, e Usuário.

Além disso, a aplicação foi construída com foco em **segurança**, integrando **autenticação via JWT** para proteger seus endpoints.

---

### ⚙️ Como Executar o Projeto Localmente

#### ✅ Pré-requisitos

Certifique-se de ter instalado:

- **Java JDK 17** ou superior  
- **Maven** (versão 3.8+)
- **IDE** como IntelliJ IDEA, Eclipse ou VS Code (com suporte a Java)

---

#### 📦 Instalação

1. Clone o repositório ou extraia o projeto:

    ```bash
    git clone https://github.com/GS-2025-1/Java.git
    ```

2. Acesse a pasta raiz do projeto:

    ```bash
    cd Java/Java
    ```

3. Compile o projeto e baixe as dependências:

    ```bash
    mvn clean install
    ```

---

#### 🚀 Executando a Aplicação

1. Rode o projeto com:

    ```bash
    mvn spring-boot:run
    ```

2. A aplicação estará disponível por padrão em:  
    [http://localhost:8080](http://localhost:8080)

3. Documentação da API via Swagger:  
    [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

### 📁 Estrutura do Projeto

```
Java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
├── pom.xml
├── mvnw
├── ...
```

---

### 🔧 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security + JWT
- Maven
- REST APIs
- Swagger (SpringDoc OpenAPI)
- Oracle Database

---

### 🔐 Regra de Autenticação JWT Implementada

- A aplicação utiliza **autenticação via JWT (JSON Web Token)** para proteger seus endpoints.
- O endpoint `/auth/login` permite que um usuário envie seu e-mail e senha e receba um token JWT válido.
- O token deve ser enviado em todas as requisições protegidas no cabeçalho HTTP:

    ```http
    Authorization: Bearer SEU_TOKEN_JWT
    ```

- O token possui validade de **30 minutos**.
- Após a expiração, um novo login deve ser realizado.
- O Swagger já suporta o botão **Authorize**, onde você pode colar o token para testar os endpoints.
- As senhas dos usuários são armazenadas no banco de dados de forma **criptografada com BCrypt**, garantindo a segurança.

Fluxo básico:

```
Usuário → /auth/login → recebe Token → usa Token nos endpoints protegidos
```

---

### 📬 Como Usar a API Localmente

Com o servidor rodando em `http://localhost:8080`, você pode acessar os seguintes endpoints:

# 📋 Tabela de Endpoints da API

| Entidade              | Método HTTP | Rota                           |
|-----------------------|-------------|--------------------------------|
| Alerta | REQUEST | /alertas |
| Alerta | GET | /paginadas |
| Alerta | GET | /todas |
| Alerta | GET | /todas_cacheable |
| Alerta | GET | /{id} |
| Alerta | POST | /inserir |
| Alerta | PUT | /atualizar/{id} |
| Alerta | DELETE | /excluir/{id} |
| Auth | REQUEST | / |
| Auth | POST | / |
| Bairro | REQUEST | /bairros |
| Bairro | GET | /paginadas |
| Bairro | GET | /todas |
| Bairro | GET | /todas_cacheable |
| Bairro | GET | /{id} |
| Bairro | POST | /inserir |
| Bairro | PUT | /atualizar/{id} |
| Bairro | DELETE | /excluir/{id} |
| Cidade | REQUEST | /cidades |
| Cidade | GET | /paginadas |
| Cidade | GET | /todas |
| Cidade | GET | /todas_cacheable |
| Cidade | GET | /{id} |
| Cidade | POST | /inserir |
| Cidade | PUT | /atualizar/{id} |
| Cidade | DELETE | /excluir/{id} |
| Endereco | REQUEST | /enderecos |
| Endereco | GET | /paginadas |
| Endereco | GET | /todas |
| Endereco | GET | /todas_cacheable |
| Endereco | GET | /{id} |
| Endereco | POST | /inserir |
| Endereco | PUT | /atualizar/{id} |
| Endereco | DELETE | /excluir/{id} |
| Estado | REQUEST | /estados |
| Estado | GET | /paginadas |
| Estado | GET | /todas |
| Estado | GET | /todas_cacheable |
| Estado | GET | /{id} |
| Estado | POST | /inserir |
| Estado | PUT | /atualizar/{id} |
| Estado | DELETE | /excluir/{id} |
| Rua | REQUEST | /ruas |
| Rua | GET | /paginadas |
| Rua | GET | /todas |
| Rua | GET | /todas_cacheable |
| Rua | GET | /{id} |
| Rua | POST | /inserir |
| Rua | PUT | /atualizar/{id} |
| Rua | DELETE | /excluir/{id} |
| UsuarioAlerta | REQUEST | /alertas_usuarios |
| UsuarioAlerta | GET | /paginadas |
| UsuarioAlerta | GET | /todas |
| UsuarioAlerta | GET | /todas_cacheable |
| UsuarioAlerta | GET | /{usuarioiId}/{alertaId} |
| UsuarioAlerta | POST | /inserir |
| UsuarioAlerta | DELETE | /excluir/{id} |
| Usuario | REQUEST | /usuarios |
| Usuario | GET | /paginadas |
| Usuario | GET | /todas |
| Usuario | GET | /todas_cacheable |
| Usuario | GET | /{id} |
| Usuario | GET | /usuario_por_email |
| Usuario | POST | /inserir |
| Usuario | PUT | /atualizar/{id} |
| Usuario | DELETE | /excluir/{id} |


> 💡 Use ferramentas como **Postman**, **Insomnia**, **curl** ou diretamente pelo navegador, através da interface Swagger (Opean API) para testar esses endpoints localmente.

---
