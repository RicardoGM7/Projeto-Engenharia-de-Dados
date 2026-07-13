# Integração de Dados da UFS

Este projeto contém um fluxo de integração ETL em Pentaho Hop para carregar dados acadêmicos da UFS a partir de arquivos CSV e persistir informações em tabelas de um data warehouse PostgreSQL.

## Objetivo

A integração tem como finalidade consolidar os dados de:

- departamentos e unidades acadêmicas;
- componentes curriculares/disciplina;
- docentes/professores;
- semestres;
- turmas.

Esses dados alimentam as dimensões e a tabela fato da estrutura de dados do projeto.

## Estrutura do projeto

Os arquivos principais estão organizados na pasta [integracao](integracao):

- [integracao/WorkflowDimensoes.hwf](integracao/WorkflowDimensoes.hwf): workflow principal que orquestra a execução das pipelines de dimensão.
- [integracao/fato_turmas.hpl](integracao/fato_turmas.hpl): pipeline responsável pela carga da tabela fato de turmas.
- [integracao/departamentoCSV.hpl](integracao/departamentoCSV.hpl): leitura do CSV de unidades acadêmicas e carga da dimensão de departamento.
- [integracao/departamentoTabela.hpl](integracao/departamentoTabela.hpl): leitura e atualização da dimensão de departamento no banco.
- [integracao/disciplinaCSV.hpl](integracao/disciplinaCSV.hpl): leitura do CSV de componentes curriculares e carga da dimensão de disciplina.
- [integracao/disciplinaTabela.hpl](integracao/disciplinaTabela.hpl): leitura e atualização da dimensão de disciplina no banco.
- [integracao/professorCSV.hpl](integracao/professorCSV.hpl): leitura do CSV de docentes e carga da dimensão de professor.
- [integracao/professsorTabela.hpl](integracao/professsorTabela.hpl): leitura e atualização da dimensão de professor no banco.
- [integracao/semestreCSV.hpl](integracao/semestreCSV.hpl): leitura dos arquivos de turmas e carga da dimensão de semestre.
- [integracao/semestreTabela.hpl](integracao/semestreTabela.hpl): leitura e atualização da dimensão de semestre no banco.
- [integracao/project-config.json](integracao/project-config.json): configuração do projeto.
- [integracao/metadata/rdbms/crud.json](integracao/metadata/rdbms/crud.json): configuração da conexão de origem.
- [integracao/metadata/rdbms/hop.json](integracao/metadata/rdbms/hop.json): configuração da conexão de destino.

Os arquivos CSV de entrada estão em:

- [integracao/uni-csv-unidades-academicas-da-ufs.csv](integracao/uni-csv-unidades-academicas-da-ufs.csv)
- [integracao/com-csv-componentes-curriculares-da-ufs.csv](integracao/com-csv-componentes-curriculares-da-ufs.csv)
- [integracao/doc-csv-docentes-da-ufs.csv](integracao/doc-csv-docentes-da-ufs.csv)
- [integracao/Turmas](integracao/Turmas)

## Fluxo da integração

1. O workflow [integracao/WorkflowDimensoes.hwf](integracao/WorkflowDimensoes.hwf) inicia a execução.
2. As pipelines de dimensão leem os CSVs e carregam os dados nas tabelas de dimensão.
3. As pipelines com sufixo "Tabela" realizam a sincronização com o banco de destino.
4. A pipeline [integracao/fato_turmas.hpl](integracao/fato_turmas.hpl) carrega a tabela fato de turmas, realizando consultas de lookup nas dimensões já carregadas.

## Tabelas alvo

O processo carrega, principalmente, as seguintes tabelas:

- dim_departamento
- dim_disciplina
- dim_professor
- dim_semestre
- fato_turma

## Requisitos

Para executar o projeto, são necessários:

- Pentaho Hop / Pentaho Data Integration instalado;
- acesso a um banco PostgreSQL;
- configuração correta das conexões "crud" e "hop" nos arquivos de metadata;
- variável de ambiente PROJECT_HOME apontando para a pasta [integracao](integracao).


## Observações

- Os arquivos de entrada estão em formato CSV e foram organizados por domínio acadêmico.
- O projeto foi estruturado para facilitar a manutenção e a evolução da carga ETL.
- Caso haja necessidade de ajustar os nomes das tabelas ou os mapeamentos, os arquivos HPL/HWF são o ponto principal de alteração.
