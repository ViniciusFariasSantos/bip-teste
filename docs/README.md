# 🚀 Sistema de Benefícios

## 📌 Sobre o projeto

Aplicação fullstack para gerenciamento de benefícios com suporte a transferências entre contas, construída com arquitetura em camadas e integração com EJB.

O sistema contempla:

* CRUD completo de benefícios
* Transferência com regras de negócio
* Integração entre Spring Boot e EJB (via WildFly)
* Frontend em Angular
* Testes automatizados

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

Frontend (Angular)
⬇️
Backend (Spring Boot - REST API)
⬇️
EJB (WildFly - Regras de negócio)
⬇️
Banco de dados

### 📂 Camadas:

* **Controller** → expõe endpoints REST
* **Service** → orquestra regras e integrações
* **Client (Integration)** → comunicação com EJB
* **EJB (WildFly)** → regras críticas (transferência, validações)
* **DTO** → transporte de dados

---

## 🛠️ Tecnologias utilizadas

### Backend:

* Java 8+
* Spring Boot
* Spring Web
* Spring Test
* JPA / Hibernate

### Frontend:

* Angular 16+

### Banco de Dados:

* SQL (schema.sql + seed.sql)

### Servidor de Aplicação:

* WildFly (Execução do módulo EJB)

### Documentação:

* Swagger (OpenAPI)

---

## ⚙️ Como executar o projeto

### 🔹 1. Banco de dados

Execute os scripts:

```bash
db/schema.sql
db/seed.sql
```

---

## 🟠 Execução do EJB (WildFly)

O módulo EJB é responsável pelas regras de negócio críticas, como validações e transferência.

### 🔹 Pré-requisito

* WildFly instalado (versão 26+ recomendada)

---

### 🔹 Deploy do EJB

```bash
cd ejb-module
mvn clean package
```

Copiar o `.jar` gerado para:

```bash
WILDFLY_HOME/standalone/deployments
```

---

### 🔹 Subir o WildFly

Linux / Mac:

```bash
cd WILDFLY_HOME/bin
./standalone.sh
```

Windows:

```bash
standalone.bat
```

---

### 🔹 Endpoint do EJB

```bash
http://localhost:8080/ejb-module/api/beneficios
```

⚠️ O backend depende do EJB em execução para funcionar corretamente.

---

### 🔹 3. Backend (Spring Boot)

```bash
cd backend-module
mvn clean install
mvn spring-boot:run
```

Aplicação disponível em:

```bash
http://localhost:8081
```

---

### 🔹 4. Frontend (Angular)

```bash
cd frontend
npm install
ng serve
```

Acesse:

```bash
http://localhost:4200
```

---

## 🔗 Endpoints da API

### 📌 Benefícios

| Método | Endpoint         | Descrição    |
| ------ | ---------------- | ------------ |
| GET    | /beneficios      | Listar todos |
| POST   | /beneficios      | Criar        |
| PUT    | /beneficios/{id} | Atualizar    |
| DELETE | /beneficios/{id} | Remover      |

---

### 🔄 Transferência

| Método | Endpoint             | Descrição              |
| ------ | -------------------- | ---------------------- |
| POST   | /beneficios/transfer | Realizar transferência |

---

## 📚 Documentação da API

Swagger disponível em:

```bash
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Testes

Foram implementados testes automatizados cobrindo:

* Controllers (API REST)
* Services (regras de negócio)
* Client (integração com EJB)
* DTOs (serialização/desserialização)
* Inicialização da aplicação

### ▶️ Executar testes

```bash
mvn test
```

---

## 📊 Critérios atendidos

| Critério               | Status |
| ---------------------- | ------ |
| Arquitetura em camadas | ✅      |
| Correção EJB           | ✅      |
| CRUD + Transferência   | ✅      |
| Qualidade de código    | ✅      |
| Testes                 | ✅      |
| Documentação           | ✅      |
| Frontend               | ✅      |

---

## 👨‍💻 Autor

Vinicius Farias
Desenvolvedor Full Stack

---

## 📬 Observações finais

Projeto desenvolvido com foco em boas práticas, separação de responsabilidades, integração entre sistemas e cobertura de testes.

Destaque para uso de **EJB com WildFly** em conjunto com **Spring Boot**, simulando um cenário real de arquitetura corporativa.
