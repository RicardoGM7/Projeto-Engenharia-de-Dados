# Projeto de Engenharia de Dados

Este repositório reúne as três partes de um trabalho acadêmico voltado à prática de armazenamento, manipulação e integração de dados. O projeto demonstra, de forma complementar, o uso de bancos relacionais e NoSQL, além de um fluxo ETL para carga de dados acadêmicos.

## Participantes do Trabalho

Davi Alves Matos

Nathan Alejandro Pereira Soares Ramirez

Ricardo Gomes Miranda

## Visão geral

O trabalho está organizado em três módulos:

1. Parte 1 - CRUD com PostgreSQL e Java
2. Parte 2 - CRUD com MongoDB e Java
3. Parte 3 - Integração de dados com Pentaho Hop

## 1. CRUD com PostgreSQL e Java

Na pasta [CRUD_PostegreSQL_Parte 1](CRUD_PostegreSQL_Parte%201), foi implementada uma aplicação em Java para realizar operações CRUD em um banco PostgreSQL utilizando JDBC.

### Objetivo
- Demonstrar o uso de Java para acessar um banco relacional;
- Implementar operações de criação, leitura, atualização e exclusão;
- Manipular dados relacionados a entidades acadêmicas como curso, estudante, usuário e vínculo.

### Tecnologias
- Java
- PostgreSQL
- JDBC

## 2. CRUD com MongoDB e Java

Na pasta [CRUD_MongoDB_Parte 2](CRUD_MongoDB_Parte%202), foi desenvolvida uma aplicação em Java que utiliza MongoDB para persistir e consultar dados por meio de operações CRUD no terminal.

### Objetivo
- Explorar o uso de um banco NoSQL;
- Trabalhar com documentos em vez de tabelas relacionais;
- Demonstrar integração entre Java e MongoDB em um cenário prático.

### Tecnologias
- Java
- Maven
- MongoDB
- MongoDB Driver

## 3. Integração de dados com Pentaho Hop

Na pasta [integracao_Parte 3](integracao_Parte%203), foi construído um fluxo de integração ETL utilizando Pentaho Hop para carregar dados acadêmicos a partir de arquivos CSV para um banco PostgreSQL.

### Objetivo
- Realizar a extração, transformação e carga (ETL) de dados;
- Consolidar informações de departamentos, disciplinas, professores, semestres e turmas;
- Estruturar uma carga dimensional para posterior análise.

### Tecnologias
- Pentaho Hop
- PostgreSQL
- Arquivos CSV
- Workflow e pipelines ETL

## Objetivo do trabalho como um todo

O projeto busca mostrar diferentes abordagens para o tratamento de dados em ambientes universitários, incluindo:
- persistência em bancos relacionais;
- persistência em banco NoSQL;
- integração e organização de dados por meio de ETL.

## Estrutura do repositório

- [CRUD_PostegreSQL_Parte 1](CRUD_PostegreSQL_Parte%201): aplicação CRUD com PostgreSQL.
- [CRUD_MongoDB_Parte 2](CRUD_MongoDB_Parte%202): aplicação CRUD com MongoDB.
- [integracao_Parte 3](integracao_Parte%203): projeto ETL com Pentaho Hop.
