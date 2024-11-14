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
