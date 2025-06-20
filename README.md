# CRUD Spring Boot Application

Uma aplicação Spring Boot completa para gerenciamento de usuários com operações CRUD, paginação, busca e recursos de segurança.

## 🚀 Funcionalidades

- ✅ CRUD completo de usuários
- ✅ Paginação e ordenação
- ✅ Busca por nome
- ✅ Validação de dados
- ✅ Tratamento de exceções global
- ✅ Rate limiting
- ✅ Configurações de segurança
- ✅ Auditoria automática (created_at, updated_at)
- ✅ Cache com Caffeine
- ✅ Logging configurado
- ✅ CORS configurado

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **Lombok**
- **Bucket4j** (Rate Limiting)
- **Caffeine** (Cache)
- **Maven**

## 📋 Pré-requisitos

- Java 17 ou superior
- MySQL 8.0 ou superior
- Maven 3.6+

## 🔧 Configuração

### 1. Clone o repositório
```bash
git clone <repository-url>
cd crud-spring-app
```

### 2. Configure o banco de dados
Crie um banco MySQL e configure as variáveis de ambiente:

```bash
export MYSQL_URL=jdbc:mysql://localhost:3306/crud_db?createDatabaseIfNotExist=true
export MYSQL_USER=seu_usuario
export MYSQL_PASSWORD=sua_senha
```

### 3. Execute a aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 API Endpoints

### Base URL: `http://localhost:8080/api/v1/users`

### 1. Criar Usuário
```http
POST /api/v1/users
Content-Type: application/json

{
  "email": "usuario@exemplo.com",
  "name": "Nome do Usuário"
}
```

### 2. Listar Todos os Usuários
```http
GET /api/v1/users
```

### 3. Listar Usuários com Paginação
```http
GET /api/v1/users/page?page=0&size=10&sortBy=name&sortDir=asc
```

### 4. Buscar Usuário por ID
```http
GET /api/v1/users/{id}
```

### 5. Buscar Usuários por Nome
```http
GET /api/v1/users/search?name=João
```

### 6. Atualizar Usuário
```http
PUT /api/v1/users/{id}
Content-Type: application/json

{
  "email": "novo@exemplo.com",
  "name": "Novo Nome"
}
```

### 7. Deletar Usuário
```http
DELETE /api/v1/users/{id}
```

## 📊 Resposta de Paginação

```json
{
  "content": [
    {
      "id": 1,
      "email": "usuario@exemplo.com",
      "name": "Nome do Usuário",
      "createdAt": "2024-01-01T10:00:00",
      "updatedAt": "2024-01-01T10:00:00"
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 1,
  "totalPages": 1,
  "hasNext": false,
  "hasPrevious": false
}
```

## 🔒 Segurança

- **Rate Limiting**: 20 requisições por minuto por IP
- **CORS**: Configurado para localhost:3000 e localhost:8080
- **Headers de Segurança**: XSS Protection, Content Security Policy
- **Validação**: Bean Validation em todos os DTOs

## 🏗️ Arquitetura

```
src/main/java/com/example/crud_spring_app/
├── config/           # Configurações (Security, Rate Limit, Web)
├── controller/       # Controllers REST
├── dto/             # Data Transfer Objects
├── exception/       # Exceções customizadas
├── model/           # Entidades JPA
├── repository/      # Repositórios Spring Data
└── service/         # Lógica de negócio
```

## 🧪 Testes

Execute os testes com:
```bash
mvn test
```

## 📝 Logs

A aplicação está configurada com logging detalhado:
- SQL queries (DEBUG)
- Spring Security (DEBUG)
- Aplicação customizada (DEBUG)

## 🔧 Configurações Avançadas

### Cache
- Cache de usuários com Caffeine
- Tamanho máximo: 500 entradas
- Expiração: 10 minutos

### Performance
- Batch size: 20
- Pool de conexões: 20
- Open-in-view: false

### Rate Limiting
- 20 requisições por minuto
- Aplicado em `/api/v1/**`

## 🚨 Tratamento de Erros

A aplicação retorna códigos HTTP apropriados:

- `200` - Sucesso
- `201` - Criado
- `400` - Dados inválidos
- `404` - Usuário não encontrado
- `409` - Email já existe
- `429` - Rate limit excedido
- `500` - Erro interno

## 📈 Monitoramento

Para monitoramento em produção, considere adicionar:
- Spring Boot Actuator
- Micrometer para métricas
- Prometheus + Grafana

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. 