# API de Biblioteca
Descrição
Este projeto é uma API de biblioteca desenvolvida utilizando Java 21, Spring Boot, Lombok, JPA Hibernate e o banco de dados H2 em memória. A API permite o gerenciamento de livros, usuários e empréstimos (loans), proporcionando operações de CRUD (Create, Read, Update, Delete) para cada uma dessas entidades. O Hibernate é utilizado para o mapeamento objeto-relacional (ORM), e o H2 é usado para simplificar o armazenamento de dados.

# Tecnologias Utilizadas
- Java 21
- Spring Boot
- Lombok (para reduzir código boilerplate)
- JPA Hibernate (para ORM)
- H2 Database (banco de dados em memória)
- Maven (gerenciamento de dependências)
  
# Requisitos
- Java 21 ou superior
- Maven instalado

# Como Rodar o Projeto
1. Clone o repositorio com comando "git clone https://github.com/Aleak1ra/biblioteca-api.git" na pasta onde você queira.
2. Instale as dependências rodando o maven na sua IDE ou com o comando "mvn clean install"
3. Run BibliotecaApiApplication

# Acesso ao console do banco de dados H2 (SSGBD)
1. Abrir o navegador e acessar a url "http://localhost:8080/h2-console"
2. Realizar o login com as credenciais configuradas no arquivo "applications.properties"

# Funcionalidades Implementadas
A API de biblioteca oferece as seguintes funcionalidades principais:

Gerenciamento de Livros:

- Adicionar novos livros.
- Listar todos os livros.
- Atualizar informações de livros.
- Remover livros do sistema.

Gerenciamento de Usuários:

- Criar novos usuários.
- Listar todos os usuários cadastrados.
- Atualizar dados dos usuários.
- Remover usuários da biblioteca.

Gerenciamento de Empréstimos:

- Registrar um novo empréstimo de livro:
Um empréstimo só pode ser registrado se o livro estiver disponível.

- Finalizar um empréstimo (devolução do livro):
Um empréstimo só pode ser finalizado se o livro foi devolvido.
- Listar todos os empréstimos ativos.

# Endpoints da API de Livros (Book)
1. Criar Livro
URL: http://localhost:8080/books/createBook

Método: POST

Descrição: Cria um novo livro no sistema. Resposta de Sucesso (200 OK).

Body de Exemplo:
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "isbn": "978-0743273565",
  "status": "AVAILABLE"
}

2. Atualizar Livro por ID
URL: http://localhost:8080/books/updateBook/{id}

Método: PUT

Descrição: Atualiza os detalhes de um livro existente com base no ID fornecido. Resposta de Sucesso (200 OK).

Parâmetros:

id: ID do livro a ser atualizado (substitua {id} pelo ID real do livro).
Body de Exemplo:
{
  "title": "Titulo atualizado",
  "author": "F. Scott Fitzgerald atualizado",
  "isbn": "978-0743273565 atualizado",
  "status": "BORROWED"
}

3. Deletar Livro por ID
URL: http://localhost:8080/books/delete/{id}

Método: DELETE

Descrição: Deleta um livro do sistema com base no ID fornecido. Resposta de Sucesso (200 OK).

Parâmetros:

id: ID do livro a ser deletado (substitua {id} pelo ID real do livro).

4. Listar Todos os Livros
URL: http://localhost:8080/books/all

Método: GET

Descrição: Retorna uma lista de todos os livros cadastrados no sistema. Resposta de Sucesso (200 OK).

# Endpoints da API de Usuários (User)
1. Criar Usuário
URL: http://localhost:8080/users/createUser

Método: POST

Descrição: Cria um novo usuário no sistema. Resposta de Sucesso (200 OK).

Body de Exemplo:
{
	"email": "teste@hotmail.com",
	"name":"Teste da Silva Junior",
  "cpf": "1234657489"
}

2. Atualizar Usuário por ID
URL: http://localhost:8080/users/updateUser/{id}

Método: PUT

Descrição: Atualiza os detalhes de um usuário existente com base no ID fornecido. Resposta de Sucesso (200 OK).

Parâmetros:

id: ID do usuário a ser atualizado (substitua {id} pelo ID real do usuário).
Body de Exemplo:
{
	"email": "teste_atualizado@hotmail.com",
	"name":"Teste da Silva Junior Atualizado"
}

3. Deletar Usuário por ID
URL: http://localhost:8080/users/delete/{id}

Método: DELETE

Descrição: Deleta um usuário do sistema com base no ID fornecido.

Parâmetros:

id: ID do usuário a ser deletado (substitua {id} pelo ID real do usuário). Resposta de Sucesso (200 OK).

4. Listar Todos os Usuários
URL: http://localhost:8080/users/all

Método: GET

Descrição: Retorna uma lista de todos os usuários cadastrados no sistema. Resposta de Sucesso (200 OK).

# Endpoints da API de Empréstimos (Loan)
1. Criar Empréstimo (Emprestar Livro)
URL: http://localhost:8080/loans/createLoan

Método: POST

Descrição: Cria um novo empréstimo de livro para um usuário. Resposta de Sucesso (200 OK).

Body de Exemplo:
{
  "book": {
    "id": 6
  },
  "user": {
    "id": 3
  },
 
  "status": "ACTIVE"
}

2. Devolver Livro (Atualizar Empréstimo)
URL: http://localhost:8080/loans/updateLoan/{id}

Método: PUT

Descrição: Finaliza um empréstimo, marcando o livro como devolvido e atualizando o status do empréstimo.

Parâmetros:

id: ID do empréstimo a ser atualizado (substitua {id} pelo ID real do empréstimo). Resposta de Sucesso (200 OK).

3. Listar Todos os Empréstimos
URL: http://localhost:8080/loans/getAllLoans

Método: GET

Descrição: Retorna uma lista de todos os empréstimos ativos no sistema. Resposta de Sucesso (200 OK).

# Endpoints da API - Relatórios e Estatísticas
1. Contar Livros com Status 'Disponível'
URL: http://localhost:8080/books/count

Método: GET

Descrição: Retorna a quantidade total de livros com o status "AVAILABLE" (disponível) no sistema. Resposta de Sucesso (200 OK):

2. Relatório de Usuários com Mais Empréstimos
URL: http://localhost:8080/loans/reports/mostUsersLoans

Método: GET

Descrição: Retorna uma lista ordenada de usuários, do que mais fez empréstimos, com a quantidade de empréstimos realizados. Resposta de Sucesso (200 OK):

3. Relatório de Livros com Mais Empréstimos
URL: http://localhost:8080/loans/reports/mostBooksLoans

Método: GET

Descrição: Retorna uma lista ordenada de livros, do que mais foi emprestado, com a quantidade de empréstimos realizados. Resposta de Sucesso (200 OK).










