## Sobre o projeto

Adopet é um site fictício de adoção de pets, com funcionalidades para cadastro de tutores, de abrigos e de pets, além de solicitação de adoções. Nesse repositório o projeto será uma API Rest em Java do Adopet.

---

## Funcionalidades

- [x] Cadastro/atualização de tutores;
- [x] Cadastro de abrigos;
- [x] Cadastro de pets do abrigo;
- [x] Listagem de pets disponíveis para adoção;
- [x] Solicitação de adoção;
- [x] Aprovação/reprovação de adoção;

---

## Layout

O projeto desse repositório é apenas a API Backend, mas existe um figma com o layout que está disponível neste link: <a href="https://www.figma.com/file/TlfkDoIu8uyjZNla1T8TpH?embed_host=notion&kind=&node-id=518%3A11&t=esSUkfGQEWUeUASj-1&type=design&viewer=1">Layout no Figma</a>

---

## Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[H2 Database](https://flywaydb.org)**

---

## Alterações

- [x] Utilizar o padrão DTO para representar os dados que chegam e saem na API;
- [x] Mover validações de uma entidade JPA para uma classe DTO;
- [x] Funcionalidades do padrão DTO e as suas vantagens;
- [x] Testar uma classe que possui dependências;
- [x] Declarar mocks com a anotação @Mock do Mockito;
- [x] Indicar atributos a serem instanciados e ter suas dependências substituídas pelos mocks, via anotação @InjectMocks;
- [x] Manipular comportamentos de objetos mocks com o Mockito, via método BDDMockito.given;
- [x] Testar uma classe Service, que coordena o fluxo de regras de negócio na aplicação;
- [x] Utilizar o recurso de ArgumentCaptor, do Mockito, para recuperar parâmetros em mocks;
- [x] Utilizar um spy no Mockito e sua diferença em relação a um mock;
- [x] Escrever testes automatizados de uma classe Controller utilizando o Spring;
- [x] Simular requisições na API com o uso da classe MockMvc do Spring;
- [x] Verificar os retornos da API nos testes automatizados.