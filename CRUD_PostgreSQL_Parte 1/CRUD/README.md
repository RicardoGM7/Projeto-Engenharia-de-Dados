# CRUD PostgreSQL com Java

Este projeto é uma aplicação console em Java para realizar operações CRUD (Create, Read, Update e Delete) em um banco de dados PostgreSQL, utilizando JDBC.

A aplicação conecta-se a uma instância PostgreSQL hospedada em uma base AWS RDS e oferece um menu interativo para manipular as tabelas:

- Curso
- Estudante
- Usuário
- Vínculo

## Objetivo

Demonstrar o uso de Java com PostgreSQL para:

- criar conexões com banco de dados;
- executar comandos SQL com PreparedStatement;
- implementar operações básicas de CRUD;
- organizar a lógica de acesso ao banco em classes separadas.

## Arquivos principais

- [CRUD/Principal.java](CRUD/Principal.java): ponto de entrada da aplicação, responsável por abrir a conexão com o banco e exibir o menu interativo.
- [CRUD/Curso.java](CRUD/Curso.java): operações CRUD referentes à tabela Curso.
- [CRUD/Estudante.java](CRUD/Estudante.java): operações CRUD referentes à tabela Estudante.
- [CRUD/Usuario.java](CRUD/Usuario.java): operações CRUD referentes à tabela Usuário.
- [CRUD/Vinculo.java](CRUD/Vinculo.java): operações CRUD referentes à tabela Vínculo.

## Configuração do banco

No arquivo [CRUD/Principal.java](CRUD/Principal.java), a conexão é configurada diretamente com:

- host do servidor PostgreSQL
- nome do banco
- usuário
- senha

É importante ajustar esses valores conforme a sua instância do PostgreSQL/AWS.

## Funcionalidades disponíveis

Ao iniciar o programa, o usuário pode escolher uma tabela e, em seguida, realizar uma das operações:

- Listar registros
- Inserir novo registro
- Remover registro
- Atualizar registro
- Voltar ao menu anterior

## Observações importantes

- As credenciais e informações de conexão estão hardcoded no código para fins didáticos.
- Em um ambiente real, recomenda-se armazenar essas informações em variáveis de ambiente ou em um arquivo de configuração separado.
- O projeto usa tipos específicos do PostgreSQL, como data, enum e outros campos associados ao schema universidade.
