<div align="center">

# 🏥 Hospital API

### API REST para gestão hospitalar — Spring Boot

<br/>

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_4.0.8_SNAPSHOT-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![H2](https://img.shields.io/badge/H2-003545?style=for-the-badge&logo=databricks&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-CA4245?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-6DB33F?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge)

</div>

---

## 📚 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Regras de Negócio](#️-regras-de-negócio)
- [Arquitetura](#️-arquitetura)
- [Tecnologias](#️-tecnologias)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#️-como-executar)
- [Banco de Dados](#️-banco-de-dados)
- [Endpoints da API](#-endpoints-da-api)
- [Exemplos de Requisições](#-exemplos-de-requisições)
- [Testes](#-testes)
- [Autora](#-autora)
- [Licença](#-licença)

---

## 🎯 Sobre o Projeto

O **Hospital API** é uma API REST desenvolvida com **Spring Boot** para gerenciamento de pacientes, médicos, consultas e internações.

O projeto foi elaborado como parte da disciplina de **Desenvolvimento de Serviços com Spring Boot**, aplicando conceitos de:

- Arquitetura em camadas (Controller, Service, Repository)
- Mapeamento objeto-relacional com JPA/Hibernate
- Validação de dados com Bean Validation
- Documentação com OpenAPI/Swagger
- Monitoramento com Spring Boot Actuator
- Testes unitários com Mockito
- Testes de integração com MockMvc e H2
- Containerização com Docker

---

## 🚀 Funcionalidades

### 👤 Pacientes

- Cadastrar paciente
- Buscar paciente por ID
- Listar todos os pacientes
- Remover paciente

### 🩺 Médicos

- Cadastrar médico
- Listar todos os médicos
- Ranking de médicos com mais consultas

### 📅 Consultas

- Cadastrar consulta (associando paciente e médico)

### 🏥 Internações

- Registro de internações vinculadas a pacientes (modelo implementado)

## 🏗️ Arquitetura

A aplicação segue uma arquitetura em **camadas tradicionais**, com clara separação de responsabilidades.

### Diagrama de Camadas

```
┌───────────────────────────────────────────┐
│             Controllers (REST)             │
│  PacienteController, MedicoController,     │
│  ConsultaController                        │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│            Services (lógica)                │
│  PacienteService, MedicoService,             │
│  ConsultaService                             │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│           Repositories (JPA)                 │
│  PacienteRepository, MedicoRepository,       │
│  ConsultaRepository, InternacaoRepository    │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│              Models / Entities               │
│   Paciente, Medico, Consulta, Internacao     │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│          Banco de Dados (PostgreSQL)         │
└───────────────────────────────────────────────┘
```

### Entidades

> 📌 Adicione aqui o diagrama de entidades do projeto (`docs/diagrama-entidades.png`).

```
![Diagrama de Entidades](docs/diagrama-entidades.png)
```

---

## 🛠️ Tecnologias

| Tecnologia             | Versão         |
| ---------------------- | -------------- |
| Java                   | 21             |
| Spring Boot            | 4.0.8-SNAPSHOT |
| Spring Data JPA        | —              |
| Spring Web MVC         | —              |
| Spring Boot Actuator   | —              |
| Spring Boot Validation | —              |
| PostgreSQL (driver)    | 42.7.11        |
| H2 Database            | _(testes)_     |
| Lombok                 | 1.18.46        |
| SpringDoc OpenAPI      | 3.0.2          |
| Maven                  | 3.9+           |
| Docker                 | —              |
| JUnit 5                | 6.0.3          |
| Mockito                | 5.20.0         |
| AssertJ                | 3.27.7         |

---

## 📂 Estrutura do Projeto

```
hospital-api/
│
├── src/main/java/br/edu/infnet/hospital_api/
│   ├── controllers/           # Endpoints REST
│   ├── dto/                   # Objetos de transferência
│   ├── models/                # Entidades JPA
│   ├── repositories/          # Interfaces JPA
│   ├── services/              # Lógica de negócio
│   └── config/                # Carga inicial de dados
│
├── src/main/resources/
│   └── application.properties # Configurações (PostgreSQL, Actuator)
│
├── src/test/java/br/edu/infnet/hospital_api/
│   ├── controller/            # Testes de integração com MockMvc
│   ├── repository/            # Testes de repositório com @DataJpaTest
│   └── service/               # Testes unitários com Mockito
│
├── Dockerfile                 # Multi-estágio (Maven + JRE)
└── pom.xml                    # Dependências e plugins
```

---

## ▶️ Como Executar

### Pré-requisitos

- [Java 21+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8+](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/) (ou variáveis de ambiente configuradas)
- [Git](https://git-scm.com/)

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/hospital-api.git
```

### 2. Entrar na pasta

```bash
cd hospital-api
```

### 3. Configurar o banco de dados

Edite `src/main/resources/application.properties` ou defina as variáveis de ambiente:

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/hospital
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

### 4. Compilar e executar

```bash
mvn clean package -DskipTests
java -jar target/app.jar
```

> A aplicação estará disponível em: **`http://localhost:8080`**

### 5. Executar com Docker

```bash
docker build -t hospital-api .

docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://host/hospital?sslmode=require" \
  -e SPRING_DATASOURCE_USERNAME="user" \
  -e SPRING_DATASOURCE_PASSWORD="pass" \
  hospital-api
```

---

## 🗄️ Banco de Dados

| Ambiente | Banco         | DDL           |
| -------- | ------------- | ------------- |
| Produção | PostgreSQL    | `update`      |
| Testes   | H2 em memória | `create-drop` |

- A aplicação cria automaticamente as tabelas no PostgreSQL usando `ddl-auto=update`.
- Os testes utilizam **H2 em memória** automaticamente quando a dependência `h2` está no classpath de testes.

### Dados Iniciais (`CommandLineRunner`)

Ao iniciar a aplicação, são inseridos automaticamente:

- **Médicos:** Dr. Carlos (Cardiologista), Dra. Ana (Ortopedista)
- **Pacientes:** João Silva, Maria Oliveira

---

## 🌐 Endpoints da API

| Método   | Endpoint                       | Descrição                  |
| -------- | ------------------------------ | -------------------------- |
| `POST`   | `/pacientes`                   | Cadastrar paciente         |
| `GET`    | `/pacientes/{id}`              | Buscar paciente por ID     |
| `GET`    | `/pacientes`                   | Listar todos os pacientes  |
| `DELETE` | `/pacientes/{id}`              | Remover paciente           |
| `POST`   | `/medicos`                     | Cadastrar médico           |
| `GET`    | `/medicos`                     | Listar todos os médicos    |
| `GET`    | `/medicos/consultas-ordenadas` | Médicos com mais consultas |
| `POST`   | `/consultas`                   | Cadastrar consulta         |
| `GET`    | `/actuator/health`             | Health check da aplicação  |

---

## 📨 Exemplos de Requisições

### Cadastrar Paciente

`POST /pacientes`

```json
{
  "nome": "Ana Maria",
  "cpf": "123.456.789-00",
  "dataNascimento": "1990-05-20",
  "telefone": "(11) 91234-5678"
}
```

### Cadastrar Médico

`POST /medicos`

```json
{
  "nome": "Dr. João",
  "crm": "CRM-12345",
  "especialidade": "Cardiologista"
}
```

### Cadastrar Consulta

`POST /consultas`

```json
{
  "pacienteId": 1,
  "medicoId": 1,
  "dataConsulta": "2026-06-25T15:00:00",
  "observacoes": "Dor de cabeça"
}
```

---

## 🧪 Testes

O projeto possui cobertura de testes em três camadas:

### Testes Unitários (Service)

- `PacienteServiceTest`, `MedicoServiceTest`, `ConsultaServiceTest`
- Utilizam **Mockito** com `@ExtendWith(MockitoExtension.class)`
- Repositórios simulados com `@Mock`

### Testes de Controlador (Controller)

- `PacienteControllerTest`, `MedicoControllerTest`, `ConsultaControllerTest`
- Utilizam **MockMvc** standalone (`MockMvcBuilders.standaloneSetup`)
- Serviços simulados com `@MockitoBean` ou `@Mock`

### Testes de Repositório (Repository)

- `PacienteRepositoryTest`, `MedicoRepositoryTest`, `ConsultaRepositoryTest`
- Utilizam `@DataJpaTest` com **H2 em memória**
- Validam persistência e recuperação de dados

### Executar testes

```bash
mvn test
```

> Os testes também podem ser executados diretamente no IntelliJ IDEA.

---

## 👩‍💻 Autora

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/LeticiaFAAGomes">
        <img src="https://github.com/LeticiaFAAGomes.png" width="80px" style="border-radius:50%"/><br/>
        <sub><b>Letícia Gomes</b></sub>
      </a>
    </td>
  </tr>
</table>

Projeto desenvolvido para a disciplina de **Desenvolvimento de Serviços com Spring Boot – Desenvolvimento de Softwares Escaláveis** (Infnet).

---

## 📄 Licença

Este projeto está licenciado sob a licença **MIT**.

---

<div align="center">

Desenvolvido com ☕ e Spring Boot 🚀

</div>
