# Projeto Java Spring Boot com React

Este é um projeto que demonstra a integração entre Java Spring Boot no backend e React no frontend, utilizando H2 como banco de dados embutido. As requisições são feitas através do Axios.

## Requisitos

- Java
- Maven
- React

## Configuração

### Backend (Spring Boot)

1. Navegue até o diretório `backend`.
2. Execute `mvn spring-boot:run` para iniciar o servidor backend.
3. O servidor estará disponível em `http://localhost:8080`.

### Frontend (React)

1. Navegue até o diretório `frontend`.
2. Execute `npm install` para instalar as dependências.
3. Execute `npm start` para iniciar o servidor de desenvolvimento do React.
4. O frontend estará disponível em `http://localhost:3000`.

## Endpoints

### Collaborators

- **GET /api/collaborators**: Retorna todos os colaboradores.
- **GET /api/collaborators/{id}**: Retorna um colaborador específico pelo ID.
- **POST /api/collaborators**: Cria um novo colaborador.
- **PUT /api/collaborators/{id}**: Atualiza um colaborador existente pelo ID.
- **DELETE /api/collaborators/{id}**: Remove um colaborador pelo ID.

### Breakfast

- **GET /api/breakfast**: Retorna informações sobre o café da manhã.
- **POST /api/breakfast/order**: Realiza um pedido para o café da manhã.

## Tecnologias Utilizadas

### Backend

- Java
- Spring Boot
- H2 Database
- Maven

### Frontend

- React
- Axios

## Contribuindo

Sinta-se à vontade para contribuir com melhorias neste projeto. Basta abrir uma issue ou enviar um pull request.


