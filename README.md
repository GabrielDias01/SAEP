# Escopo do Projeto: Sistema de Gerenciamento de Tarefas

## 1. Contextualização

Uma indústria alimentícia utiliza o **Kanban** para o gerenciamento das tarefas nos seus setores, mas de forma simplificada, sem um mapeamento detalhado do fluxo de trabalho. Atualmente, utiliza-se um modelo de **to-do list** com apenas três status para as tarefas: **a fazer**, **fazendo** e **pronto**.

A empresa deseja melhorar o sistema atual, aumentando a visibilidade das tarefas e permitindo uma integração entre os setores. Para isso, será necessário criar uma aplicação que facilite o gerenciamento das tarefas, incluindo o cadastro e atualização de informações relacionadas aos usuários e suas respectivas tarefas.

## 2. Objetivo

Desenvolver um sistema que permita o gerenciamento de tarefas no formato **to-do list**, com a possibilidade de:

- Cadastrar usuários e associá-los a tarefas.
- Gerenciar o status e a prioridade das tarefas.
- Visualizar as tarefas em uma tabela organizada por status.
- Integrar as informações entre os setores.

## 3. Requisitos Funcionais

### 3.1 Cadastro de Usuários

- O sistema deve permitir o cadastro de usuários com as seguintes informações:
  - **id**: Identificador único do usuário.
  - **nome**: Nome completo do usuário.
  - **e-mail**: Endereço de e-mail do usuário.
  - **senha**: Senha para autenticação (não será implementado o controle de acesso, mas será necessário armazenar a senha).

- A validação do cadastro deve garantir que todos os campos sejam obrigatórios e que o e-mail tenha um formato válido.

- Após o cadastro, uma mensagem de sucesso deve ser exibida.

### 3.2 Cadastro de Tarefas

- O sistema deve permitir que os usuários criem tarefas com as seguintes informações:
  - **id da tarefa**: Identificador único da tarefa.
  - **id do usuário**: Relacionamento da tarefa com um usuário específico.
  - **descrição da tarefa**: Texto descrevendo a tarefa.
  - **nome do setor**: Setor responsável pela tarefa.
  - **prioridade**: Prioridade da tarefa (baixa, média, alta).
  - **data de cadastro**: Data em que a tarefa foi registrada.
  - **status**: Status da tarefa, que será definido como "a fazer" por padrão.

### 3.3 Gerenciamento de Tarefas

- O sistema deve permitir que o usuário visualize uma lista de tarefas com os seguintes filtros:
  - **Por status**: Exibição das tarefas com os status "a fazer", "fazendo" ou "pronto".
  - **Alterar status e prioridade**: O usuário pode alterar o status de uma tarefa (de "a fazer" para "fazendo" ou de "fazendo" para "pronto") e a prioridade.
  - **Edição de tarefas**: O usuário pode editar as informações de uma tarefa.
  - **Exclusão de tarefas**: O usuário pode excluir tarefas já cadastradas.

### 3.4 Exibição das Tarefas

- As tarefas devem ser exibidas em uma **tabela**, organizada de forma que cada **status** (a fazer, fazendo, pronto) tenha sua própria coluna.

## 4. Requisitos Não Funcionais

- **Segurança**: Embora o controle de acesso (login/sessão) não seja necessário, a segurança dos dados no banco de dados deve ser garantida.
- **Usabilidade**: As interfaces devem ser simples e fáceis de usar, garantindo uma boa experiência para os usuários.
- **Desempenho**: O sistema deve ser rápido ao exibir e atualizar as tarefas.

## 5. Entregas

### 5.1 Diagrama de Entidade-Relacionamento (DER)

- Criar um **Diagrama de Entidade-Relacionamento** (DER) que represente o modelo lógico do banco de dados, incluindo tabelas, campos e relações.

### 5.2 Criação do Banco de Dados

- Criar a estrutura do banco de dados, conforme o DER, e fornecer o script de criação das tabelas.
- Exportar o banco de dados para um arquivo `.SQL` ou outro formato acordado.

### 5.3 Caso de Uso do Cenário de Gerenciamento de Tarefas

- Criar um **Diagrama de Caso de Uso** que ilustre os atores e as ações no sistema, como a criação, visualização e atualização das tarefas.

### 5.4 Tela de Cadastro de Usuários

- Criar uma **tela de cadastro de usuários** que permita a inserção das informações obrigatórias (nome, e-mail, senha) e valide o formato do e-mail.
- Após o cadastro, exibir uma mensagem de sucesso e garantir a persistência dos dados no banco de dados.

### 5.5 Tela de Cadastro de Tarefas

- Criar uma **tela de cadastro de tarefas** que permita o usuário inserir as informações sobre a tarefa (descrição, setor, prioridade, etc.) e associá-las ao usuário.
- A tarefa deve ser inserida com o status **"a fazer"** por padrão.

### 5.6 Tela de Gerenciamento de Tarefas

- Criar uma **tela de gerenciamento de tarefas** com a possibilidade de:
  - Visualizar as tarefas por status.
  - Alterar o status e a prioridade das tarefas.
  - Editar ou excluir tarefas existentes.

## 6. Diagrama de Relacionamento entre as Tabelas

### Tabelas:

#### **Tabela: users**
- **id** (PK): Identificador único do usuário.
- **name**: Nome do usuário.
- **email**: E-mail do usuário (único).
- **senha**: Senha do usuário.

#### **Tabela: tasks**
- **id** (PK): Identificador único da tarefa.
- **user_id** (FK): Relacionamento com a tabela `users`.
- **descricao**: Descrição da tarefa.
- **setor**: Setor responsável pela tarefa.
- **prioridade**: Prioridade da tarefa.
- **data_cadastro**: Data em que a tarefa foi cadastrada.
- **status**: Status da tarefa (a fazer, fazendo, pronto).

---

## 7. Cronograma de Execução

### Etapas do Projeto:

| Etapa                              | Descrição                                                                 | Tempo Estimado |
|------------------------------------|---------------------------------------------------------------------------|----------------|
| **1. Diagrama de Entidade-Relacionamento**  | Criar o Diagrama de Entidade-Relacionamento do banco de dados.            | 10 minutos     |
| **2. Criação do Banco de Dados**        | Criar a estrutura do banco de dados e exportar o script de criação.       | 20 minutos     |
| **3. Caso de Uso do Cenário**           | Criar o Diagrama de Caso de Uso para o gerenciamento de tarefas.          | 20 minutos     |
| **4. Tela de Cadastro de Usuários**     | Criar a tela de cadastro de usuários com validação e persistência.        | 30 minutos     |
| **5. Tela de Cadastro de Tarefas**      | Criar a tela de cadastro de tarefas, associando-as a usuários.            | 40 minutos     |
| **6. Tela de Gerenciamento de Tarefas** | Criar a tela de gerenciamento com opções de visualização, edição e exclusão de tarefas. | 60 minutos     |

---

## 8. Tecnologias Utilizadas

- **Frontend**: HTML, CSS, JavaScript (com framework se necessário, como React ou Vue.js).
- **Backend**: Pode ser desenvolvido em qualquer linguagem (PHP, Node.js, Python, etc.).
- **Banco de Dados**: MySQL, PostgreSQL ou outro banco relacional.
- **Validação**: Validação de formulários com JavaScript.

---

## 9. Conclusão

Este projeto visa criar uma aplicação simples para o gerenciamento de tarefas, com foco na usabilidade e integração entre os setores da empresa. O sistema permitirá o cadastro e gerenciamento de tarefas de forma eficaz e visual, proporcionando maior transparência nas atividades dos usuários.

Diagrama de Caso de Uso:

[Usuário] -- (Criar Tarefa)
             -- (Visualizar Tarefa)
             -- (Alterar Status da Tarefa)
             -- (Visualizar Lista de Tarefas)



Diagrama de Classes:
+-------------------------+
|        User              |
+-------------------------+
| - id: int                |
| - name: String           |
| - email: String          |
| - senha: String          |
+-------------------------+
| + getId(): int           |
| + setId(int): void       |
| + getName(): String      |
| + setName(String): void  |
| + getEmail(): String     |
| + setEmail(String): void |
| + getSenha(): String     |
| + setSenha(String): void |
| + toString(): String     |
+-------------------------+

        | 1
        |
        |*
+-------------------------+
|        Task              |
+-------------------------+
| - id: int                |
| - userId: int            |
| - descricao: String      |
| - setor: String          |
| - prioridade: String     |
| - dataCadastro: LocalDate|
| - status: String         |
+-------------------------+
| + getId(): int           |
| + setId(int): void       |
| + getUserId(): int       |
| + setUserId(int): void   |
| + getDescricao(): String |
| + setDescricao(String): void |
| + getSetor(): String     |
| + setSetor(String): void |
| + getPrioridade(): String |
| + setPrioridade(String): void |
| + getDataCadastro(): LocalDate |
| + setDataCadastro(LocalDate): void |
| + getStatus(): String    |
| + setStatus(String): void|
| + toString(): String     |
+-------------------------+
