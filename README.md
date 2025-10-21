# 💳 API de Pagamentos

## 📘 Descrição

Esta API foi desenvolvida para gerenciar transações de **cartões e pagamentos**, aplicando boas práticas de **desenvolvimento em camadas**, **tratamento de exceções customizadas**, **validações com Bean Validation** e **integração com banco de dados Oracle** via **Spring Data JPA**.

---

## 🧩 Estrutura do Projeto

O projeto segue o padrão MVC (Model-View-Controller), organizado da seguinte forma:

- **Model** → Contém as entidades e regras de negócio principais.  
- **Repository** → Responsável pela comunicação com o banco de dados via JPA.  
- **Service** → Centraliza a lógica de negócio e o tratamento de exceções personalizadas.  
- **Controller** → Disponibiliza os endpoints REST para interação com a aplicação.

---

## ⚙️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Validation**
- **Oracle Database**
- **Postman** (para testes)
- **Maven**

---

## 🚨 Tratamento de Exceções

Foram criadas duas exceções personalizadas para o tratamento centralizado de erros:

- **CartaoException** → Cuida das regras de negócio relacionadas à entidade **Cartão**.  
- **PagamentoException** → Cuida das regras de negócio relacionadas à entidade **Pagamento**.  

Essas exceções são gerenciadas de forma centralizada para garantir mensagens consistentes e tratativas adequadas em erros de regra de negócio.

---

## ⚠️ Tratamento Centralizado de Erros

A aplicação conta com um tratamento global de exceções por meio da classe GlobalExceptionHandler, que garante respostas HTTP consistentes e informativas.

Principais casos tratados:

  - **Erro de validação (400)** — MethodArgumentNotValidException
    Retorna uma lista detalhada de campos inválidos e mensagens explicativas.

  - **Erro interno (500)** — Exception
    Captura falhas não previstas e retorna uma mensagem genérica com o timestamp e a causa.

Justificativa da escolha:

  **Prós**:

  - Centraliza o tratamento de exceções, reduzindo duplicidade de código.

  - Melhora a clareza das respostas retornadas ao cliente.

  - Facilita manutenção e escalabilidade.

**Contras**:

  - Pode mascarar erros se não houver logs adequados.

  - Requer atenção para não capturar exceções de forma muito genérica.


## 🧾 Integração com Banco de Dados

A API realiza a persistência e recuperação de dados por meio do **Spring Data JPA**, com **banco de dados Oracle**.  
A conexão é configurada no arquivo `application.properties`, onde devem ser informadas as credenciais do banco:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## ✅ Validações

As entidades da aplicação utilizam Bean Validation para garantir a integridade das informações recebidas.
Campos obrigatórios e restrições de formato são validados automaticamente antes da persistência no banco.

## 🚀 Endpoints Principais
## 🔹 Cartão

| Método HTTP | Rota                             | Descrição                                             | Corpo da Requisição           | Retorno Esperado                                            |
| ----------- | -------------------------------- | ----------------------------------------------------- | ----------------------------- | ----------------------------------------------------------- |
| **GET**     | `/api/v1/cartoes`                | Lista todos os cartões cadastrados                    | —                             | Lista de objetos `Cartao`                                   |
| **POST**    | `/api/v1/cartoes`                | Registra um novo cartão no sistema                    | JSON com os dados do cartão   | Confirmação de criação                                      |
| **PUT**     | `/api/v1/cartoes/{id}`           | Atualiza completamente um cartão existente            | JSON com os dados atualizados | Confirmação de atualização                                  |
| **DELETE**  | `/api/v1/cartoes/{id}`           | Remove um cartão do banco de dados pelo ID            | —                             | Confirmação de remoção                                      |
| **GET**     | `/api/v1/cartoes/validacao/{id}` | Verifica se o cartão com o ID informado está expirado | —                             | JSON informando se o cartão está **válido** ou **expirado** |


## 🔹 Pagamento

| Método HTTP | Rota                                   | Descrição                                                                    | Corpo da Requisição            | Retorno Esperado                   |
| ----------- | -------------------------------------- | ---------------------------------------------------------------------------- | ------------------------------ | ---------------------------------- |
| **GET**     | `/api/v1/pagamentos`                   | Lista todos os pagamentos cadastrados                                        | —                              | Lista de objetos `Pagamento`       |
| **POST**    | `/api/v1/pagamentos`                   | Registra um novo pagamento no sistema                                        | JSON com os dados do pagamento | Confirmação de criação             |
| **PUT**     | `/api/v1/pagamentos/{id}`              | Atualiza completamente um pagamento existente                                | JSON com os dados atualizados  | Confirmação de atualização         |
| **DELETE**  | `/api/v1/pagamentos/{id}`              | Remove um pagamento do banco de dados pelo ID                                | —                              | Confirmação de remoção             |
| **PATCH**   | `/api/v1/pagamentos/cancelamento/{id}` | Cancela um pagamento de crédito ou débito que ainda não tenha sido cancelado | —                              | Confirmação de cancelamento        |
| **GET**     | `/api/v1/pagamentos/total`             | Calcula o valor total de pagamentos realizados no dia atual                  | —                              | JSON contendo o valor total diário |


## ▶️ Como Executar o Projeto

1. Clonar o repositório: ```git clone https://github.com/seuusuario/nome-do-projeto.git```
2. Configurar o banco de dados
3. Edite o arquivo application.properties com o seu username e password.
4. Executar o projeto
5. Utilize o Postman (ou outra ferramenta) para testar as rotas HTTP.
6. Configure o JSON necessário nos métodos POST e PUT de acordo com as entidades.

Exemplo de body: 
  - Pagamento:
      ```{
    {"formaPagamento": "PIX",
    "valor": 500,
    "dataPagamento": "2025-07-11T18:45:00"}
  
  - Cartão:
    ```{
    {"numeroCartao": "2759859812129831",
    "nomeTitular": "Guilherme Ravioli",
    "validade": "03-29",
    "cvv": "548",
    "senha": "450912"}

## 📈 Futuras Melhorias

 - Exibir mensagens mais descritivas nos retornos das requisições.

 - Reforçar as regras de negócio com validações adicionais.

 - Implementar documentação automática com Swagger/OpenAPI.

 - Adicionar testes unitários e de integração.
