# CRUD com Java e MongoDB

Este projeto é uma aplicação em Java que utiliza o banco de dados **MongoDB** para realizar operações CRUD (Criar, Ler, Atualizar e Excluir) por meio do terminal.

A aplicação foi desenvolvida para gerenciar entidades relacionadas ao contexto universitário, como:

- Usuários
- Estudantes
- Cursos
- Vínculos

## Tecnologias utilizadas

- Java 17
- Maven
- MongoDB Driver Sync
- MongoDB remoto (no cenário atual, a instância utilizada está hospedada na AWS)

## Estrutura do projeto

- `src/main/java/com/projetomongo` — código principal da aplicação
- `src/main/java/com/projetomongo/model` — classes de modelo
- `src/main/java/com/projetomongo/repository` — repositórios com operações no banco
- `pom.xml` — configuração do Maven e dependências

## Funcionalidades

O sistema oferece menus para:

- inserir, listar, atualizar e excluir usuários, estudantes, cursos e vínculos;


## Observação

- Este projeto é um exemplo prático de integração entre Java e MongoDB, com foco em operações básicas de persistência e interação via console.
- A conexão com o banco é definida diretamente no código principal da aplicação, utilizando uma instância remota do MongoDB.