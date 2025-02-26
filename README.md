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
- Java 17 ou superior
- Maven instalado

# Como Rodar o Projeto
1- Clone o repositorio com comando "git clone https://github.com/Aleak1ra/biblioteca-api.git" na pasta onde você queira.
2- Instale as dependências rodando o maven na sua IDE ou com o comando "mvn clean install"

# Acesso ao console do banco de dados H2 (SSGBD)
- Abrir o navegador e acessar a url "http://localhost:8080/h2-console"
- Realizar o login com as credenciais configuradas no arquivo "applications.properties"

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
-Listar todos os empréstimos ativos.


