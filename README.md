# ♿ Conecta Acesso

**Tecnologia conectando pessoas, estabelecimentos e acessibilidade.**

O Conecta Acesso é uma solução CitizenTech que busca facilitar a identificação de barreiras de acessibilidade em estabelecimentos comerciais e serviços, aproximando cidadãos, estabelecimentos e parceiros especializados.

---

## 📌 Sobre o projeto

O projeto foi desenvolvido como parte da Global Solution 2026 da FIAP, com a proposta de utilizar a tecnologia para contribuir com a resolução de um problema real da sociedade.

Pessoas com deficiência frequentemente encontram dificuldades para acessar e utilizar estabelecimentos comerciais e serviços devido à presença de barreiras físicas e estruturais.

Além disso, muitas dessas dificuldades não são registradas ou possuem poucas informações disponíveis para que os responsáveis pelos estabelecimentos possam buscar soluções.

O **Conecta Acesso** propõe uma plataforma para organizar essas informações, permitindo que cidadãos registrem dificuldades de acessibilidade e que estabelecimentos identifiquem as barreiras existentes, acompanhando seu gerenciamento e encontrando parceiros especializados.

## 🎯 Objetivo

Desenvolver uma plataforma que facilite a identificação e o gerenciamento de barreiras de acessibilidade, contribuindo para ambientes mais inclusivos e acessíveis.

A solução busca:

- Facilitar o registro de dificuldades de acessibilidade.
- Organizar informações sobre barreiras encontradas em estabelecimentos.
- Permitir o acompanhamento das barreiras identificadas.
- Conectar estabelecimentos a parceiros especializados em acessibilidade.

## ⚙️ Funcionalidades implementadas

O backend possui quatro módulos principais:

### 1. Estabelecimentos

Responsável pelo gerenciamento dos estabelecimentos cadastrados na plataforma.

- Cadastro de estabelecimentos.
- Consulta de estabelecimentos.
- Atualização de informações.
- Exclusão de registros.

### 2. Barreiras

Responsável pelo registro e gerenciamento das barreiras de acessibilidade identificadas nos estabelecimentos.

- Cadastro de barreiras vinculadas a estabelecimentos.
- Classificação por tipo de barreira.
- Definição de severidade.
- Gerenciamento de status.
- Consulta e atualização das barreiras.

### 3. Parceiros

Responsável pelo gerenciamento de profissionais e empresas especializadas em acessibilidade.

- Cadastro de parceiros.
- Definição de especialidade.
- Armazenamento de informações de contato.
- Consulta, atualização e exclusão de parceiros.

### 4. Relatos

Responsável pelo gerenciamento de relatos de dificuldades de acessibilidade realizados pelos cidadãos.

- Registro de relatos vinculados a estabelecimentos.
- Descrição das dificuldades encontradas.
- Gerenciamento do status dos relatos.
- Consulta, atualização e exclusão de registros.

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Java 21 | Linguagem de programação |
| Spring Boot 4 | Framework principal |
| Spring Web | Desenvolvimento da API REST |
| Spring Data JPA | Persistência e acesso aos dados |
| Hibernate | Mapeamento objeto-relacional |
| MySQL 8.4 | Banco de dados |
| Flyway | Gerenciamento de migrations |
| Maven | Gerenciamento de dependências |
| Docker | Containerização do banco de dados |
| Lombok | Redução de código repetitivo |
| Bean Validation | Validação dos dados de entrada |
| Springdoc OpenAPI | Documentação automática da API |
| Swagger UI | Interface de documentação e testes dos endpoints |
| JUnit 5 | Testes automatizados |
| Mockito | Simulação de dependências nos testes |

---

## 🏗️ Arquitetura do projeto

O backend foi organizado utilizando uma arquitetura em camadas, separando as responsabilidades de cada parte da aplicação.

```text
src/
├── main/
│   ├── java/
│   │   └── br/com/fiap/conecta_acesso/
│   │       ├── controller/
│   │       ├── domain/
│   │       │   ├── estabelecimento/
│   │       │   ├── barreira/
│   │       │   ├── parceiro/
│   │       │   └── relato/
│   │       ├── service/
│   │       └── infra/
│   │           └── exception/
│   │
│   └── resources/
│       ├── db/
│       │   └── migration/
│       └── application.properties/
│
└── test/
    └── java/
        └── br/com/fiap/conecta_acesso/
            └── service/
```

### Responsabilidades das camadas

- **Controller:** responsável por receber as requisições HTTP e disponibilizar os endpoints REST.
- **Domain:** contém as entidades, DTOs, enums e repositórios responsáveis pela estruturação do domínio.
- **Service:** concentra as regras de negócio e a comunicação entre as camadas.
- **Infra:** contém componentes de infraestrutura, incluindo o tratamento global de exceções.
- **Resources:** armazena as configurações da aplicação e os scripts de migração do banco de dados.
- **Test:** contém os testes unitários das classes de serviço.

---

## 🗄️ Banco de dados e migrações

O projeto utiliza **MySQL 8.4** como banco de dados relacional.

A persistência é realizada utilizando Spring Data JPA e Hibernate, permitindo o mapeamento das entidades Java para as tabelas do banco.

As alterações na estrutura do banco são controladas pelo **Flyway**, responsável por executar migrations versionadas.

O Hibernate utiliza a configuração `ddl-auto=validate`, verificando se a estrutura do banco está compatível com as entidades da aplicação.

O banco de dados é executado em um container Docker, facilitando a configuração do ambiente de desenvolvimento.

---

## 🚀 Como executar o projeto

### Pré-requisitos

Antes de executar a aplicação, é necessário possuir:

- Java 21 instalado.
- Docker instalado e em execução.
- Git instalado.

### 1. Clonar o repositório

```bash
git clone URL_DO_REPOSITORIO
```

Entre na pasta do projeto:

```bash
cd conecta-acesso
```

### 2. Iniciar o banco de dados

Na raiz do projeto, execute:

```bash
docker compose up -d
```

O Docker Compose iniciará o MySQL 8.4, disponibilizando a conexão na porta `3307` da máquina local.

### 3. Configurar a aplicação

No arquivo `src/main/resources/application.properties`, configure a conexão com o banco:

```properties
spring.application.name=conecta-acesso

spring.datasource.url=jdbc:mysql://localhost:3307/conecta_acesso
spring.datasource.username=conecta
spring.datasource.password=conecta123

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true
```

### 4. Executar a aplicação

Na raiz do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Após a inicialização, a aplicação estará disponível em:

```text
http://localhost:8080
```

As migrations pendentes serão executadas automaticamente pelo Flyway durante a inicialização.

---

## 📖 Documentação da API

A documentação dos endpoints é gerada automaticamente utilizando Springdoc OpenAPI e disponibilizada pelo Swagger UI.

Com a aplicação em execução, acesse:

**http://localhost:8080/swagger-ui/index.html**

Pelo Swagger é possível visualizar os endpoints, consultar os parâmetros, verificar os modelos de requisição e resposta e executar chamadas diretamente pela interface.

### Principais endpoints

| Módulo | Endpoint |
|---|---|
| Estabelecimentos | `/api/estabelecimentos` |
| Barreiras | `/api/barreiras` |
| Parceiros | `/api/parceiros` |
| Relatos | `/api/relatos` |

Os quatro módulos disponibilizam operações de criação, consulta, atualização e exclusão de registros.

---

## 🧪 Testes automatizados

O projeto possui testes unitários desenvolvidos utilizando **JUnit 5 e Mockito**, com foco na validação do comportamento das classes de serviço.

Atualmente, foram implementados 20 testes unitários:

| Classe de teste | Quantidade |
|---|---:|
| EstabelecimentoServiceTest | 5 |
| BarreiraServiceTest | 5 |
| ParceiroServiceTest | 5 |
| RelatoServiceTest | 5 |
| **Total** | **20** |

Os testes verificam operações de cadastro, atualização e exclusão, além do tratamento de situações em que os registros ou estabelecimentos relacionados não são encontrados.

---

## 👥 Equipe

| **Integrante** | **RM** |
|---|---:|
| Andre Queiroz | **554503** |
| Paulo Poças | **556080** |
| Rafael Bocchi | **557603** |
| Rafael Oliveira | **554736** |
| Marcus Vinicius | **555490** |

---

## 🎓 Instituição

**FIAP — Faculdade de Informática e Administração Paulista**

Curso: Engenharia de Software

Projeto desenvolvido para a **Global Solution 2026**.