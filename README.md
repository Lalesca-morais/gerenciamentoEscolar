
---

# Sistema de Gerenciamento Escolar

Este é um sistema de gerenciamento escolar desenvolvido em Java, que permite gerenciar informações sobre alunos, professores, cursos e matrículas. O sistema utiliza um banco de dados PostgreSQL para armazenar os dados.

## Configuração do Banco de Dados

Certifique-se de ter um banco de dados PostgreSQL configurado e atualizado. Você pode configurar as informações de conexão com o banco de dados no arquivo `config.properties`.

```properties
db.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
db.usuario=seu_usuario
db.senha=sua_senha
```

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

### Alunos

- Inserir um novo aluno
- Listar todos os alunos
- Alterar informações de um aluno
- Deletar um aluno

### Professores

- Inserir um novo professor
- Listar todos os professores
- Alterar informações de um professor
- Deletar um professor

### Cursos

- Inserir um novo curso
- Listar todos os cursos
- Alterar informações de um curso
- Deletar um curso

### Matrículas

- Inserir uma nova matrícula
- Listar todas as matrículas

### Consultas Especiais

- Consultar cursos ministrados por um professor específico
- Consultar alunos que não estão matriculados em nenhum curso
- Consultar cursos sem alunos matriculados
- Consultar alunos matriculados em mais de um curso

## Como Usar

1. Clone o repositório para o seu ambiente de desenvolvimento.
2. Configure as informações do banco de dados no arquivo `config.properties`.
3. Execute o programa principal `GerenciamentoEscolarApp` para acessar o menu principal.

## Requisitos

- Java 8 ou superior
- PostgreSQL

## Notas

- Este projeto utiliza JDBC para se conectar ao banco de dados PostgreSQL.
- Certifique-se de criar as tabelas no banco de dados usando as instruções SQL fornecidas no arquivo `create_tables.sql`.
- Lembre-se de substituir as informações de conexão com o banco de dados pelos valores corretos.

---
