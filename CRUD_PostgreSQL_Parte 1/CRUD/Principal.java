import java.sql.*;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String host = "pg-nuvem1.cv2fighqz9cm.us-east-1.rds.amazonaws.com:5432"; 
        String db = "postgres"; 
        String url = "jdbc:postgresql://" + host + "/" + db;
        String user = "postgres"; 
        String senha = "1Grupo7!"; // Insira sua senha real da AWS

        // Scanner para ler o teclado do usuário
        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = DriverManager.getConnection(url, user, senha)) {
            System.out.println("Conectado à AWS com sucesso!");

            // Instancia as suas classes separadas
            Curso curso = new Curso(conexao);
            Estudante estudante = new Estudante(conexao);
            Usuario usuario = new Usuario(conexao);
            Vinculo vinculo = new Vinculo(conexao);
            // EstudanteDAO estudanteDAO = new EstudanteDAO(conexao);
            // VinculoDAO vinculoDAO = new VinculoDAO(conexao);

            int opcao = -1;

            // Laço de repetição que mantém o menu ativo
            while (opcao != 0) {
                System.out.println("\n====================================");
                System.out.println("      SISTEMA UNIVERSIDADE - CRUD     ");
                System.out.println("====================================");
                System.out.println("Selecione a tabela que deseja trabalhar");
                System.out.println("1- Tabela Curso");
                System.out.println("2- Tabela Estudante");
                System.out.println("3- Tabela Usuário");
                System.out.println("4- Tabela Vínculo");
                System.out.println("0- Sair");
                System.out.print("Escolha uma opção: ");
                
                opcao = teclado.nextInt();
                teclado.nextLine(); // Limpa o buffer do teclado após ler o número

                switch (opcao) {
                    case 1:
                        while(true){
                            System.out.println("\n--- Tabela Curso ---");
                            System.out.println("O que deseja fazer?");
                            System.out.println("1- Listar cursos");
                            System.out.println("2- Inserir curso");
                            System.out.println("3- Remover curso");
                            System.out.println("4- Atualizar curso");
                            System.out.println("0- Voltar");
                            System.out.print("Escolha uma opção: ");

                            int op = teclado.nextInt();
                            teclado.nextLine();

                            if(op == 0) break;

                            switch (op){
                                case 1:
                                    curso.listar();
                                    break;
                                
                                case 2:
                                    System.out.print("\nNome do Curso (ex: Ciência da Computação): ");
                                    String nomeCurso = teclado.nextLine();
                            
                                    System.out.print("Grau (Bacharelado / Licenciatura Plena): ");
                                    String grau = teclado.nextLine();
                                    
                                    System.out.print("Turno (Matutino / Vespertino / Noturno): ");
                                    String turno = teclado.nextLine();
                                    
                                    System.out.print("Campus (ex: São Cristóvão): ");
                                    String campus = teclado.nextLine();
                                    
                                    System.out.print("Nível (Graduação / Mestrado / Doutorado): ");
                                    String nivel = teclado.nextLine();

                                    
                                    curso.inserir(nomeCurso, grau, turno, campus, nivel);
                                    break;

                                case 3:
                                    System.out.print("\nDigite o id do curso a ser removido: ");
                                    int idCurso = teclado.nextInt();
                                    teclado.nextLine();

                                    curso.remover(idCurso);
                                    break;

                                case 4:
                                    System.out.print("\nDigite o id do curso a ser atualizado: ");
                                    int idCurs = teclado.nextInt();
                                    teclado.nextLine();

                                    System.out.print("Digite a coluna a ser atualizada: ");
                                    String coluna = teclado.nextLine();
                                    
                                    System.out.print("Digite o novo valor da coluna: ");
                                    String atualizacao = teclado.nextLine();

                                    curso.atualizar(idCurs, coluna, atualizacao);
                                    break;

                                default:
                                    System.out.println("\nOpção inválida");
                                    break;
                            }
                        }
                        break;
                        
                        
                    case 2:
                        while(true){
                            System.out.println("\n--- Tabela Estudante ---");
                            System.out.println("O que deseja fazer?");
                            System.out.println("1- Listar estudantes");
                            System.out.println("2- Inserir estudante");
                            System.out.println("3- Remover estudante");
                            System.out.println("4- Atualizar estudante");
                            System.out.println("0- Voltar");
                            System.out.print("Escolha uma opção: ");

                            int op = teclado.nextInt();
                            teclado.nextLine();

                            if(op == 0) break;

                            switch (op){
                                case 1:
                                    estudante.listar();
                                    break;
                                
                                case 2:
                                    System.out.print("\nMatrícula do estudante: ");
                                    String matricula = teclado.nextLine();
                            
                                    System.out.print("CPF do estudante: ");
                                    long cpf = teclado.nextLong();
                                    teclado.nextLine();
                                    
                                    System.out.print("Média do aluno: ");
                                    double mc = teclado.nextDouble();
                                    teclado.nextLine();
                                    
                                    System.out.print("Ano de ingresso: ");
                                    int anoIngresso = teclado.nextInt();

                                    
                                    estudante.inserir(matricula, cpf, mc, anoIngresso);
                                    break;

                                case 3:
                                    System.out.print("Digite a matrícula do estudante a ser removido: ");
                                    String matri = teclado.nextLine();

                                    estudante.remover(matri);
                                    break;

                                case 4:
                                    System.out.print("Digite a matricula a ser atualizado: ");
                                    String mat = teclado.nextLine();
                                    
                                    System.out.print("Digite a coluna a ser atualizada: ");
                                    String coluna = teclado.nextLine();
                                    
                                    System.out.print("Digite o novo valor da coluna: ");
                                    String atualizacao = teclado.nextLine();

                                    estudante.atualizar(mat, coluna, atualizacao);
                                    break;

                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                        }
                        break;


                    case 3:
                        while(true){
                            System.out.println("\n--- Tabela Usuário ---");
                            System.out.println("O que deseja fazer?");
                            System.out.println("1- Listar usuários");
                            System.out.println("2- Inserir usuário");
                            System.out.println("3- Remover usuário");
                            System.out.println("4- Atualizar usuário");
                            System.out.println("0- Voltar");
                            System.out.print("Escolha uma opção: ");

                            int op = teclado.nextInt();
                            teclado.nextLine();

                            if(op == 0) break;

                            switch (op){
                                case 1:
                                    usuario.listar();
                                    break;
                                
                                case 2:
                                    System.out.print("CPF (somente números): ");
                                    long cpf = teclado.nextLong();
                                    teclado.nextLine(); 
                                    
                                    System.out.print("Nome Completo: ");
                                    String nomeUser = teclado.nextLine();
                                    
                                    System.out.print("Data de Nascimento (AAAA-MM-DD): ");
                                    String dataNasc = teclado.nextLine();
                                    
                                    System.out.print("E-mail principal: ");
                                    String email = teclado.nextLine();
                                    
                                    System.out.print("Telefone: ");
                                    String telefone = teclado.nextLine();
                                    
                                    System.out.print("Login de acesso: ");
                                    String login = teclado.nextLine();
                                    
                                    System.out.print("Senha: ");
                                    String senhaUser = teclado.nextLine();

                                    
                                    usuario.inserir(cpf, nomeUser, dataNasc, email, telefone, login, senhaUser);
                                    break;

                                case 3:
                                    System.out.print("Digite o CPF do usuário a ser removido: ");
                                    long cpfRemover = teclado.nextLong();
                                    teclado.nextLine();

                                    usuario.remover(cpfRemover);
                                    break;

                                case 4:
                                    System.out.print("Digite o CPF do usuário a ser atualizado: ");
                                    long cpfAtualizar = teclado.nextLong();
                                    teclado.nextLine();

                                    System.out.print("Digite a coluna a ser atualizada: ");
                                    String coluna = teclado.nextLine();
                                    
                                    System.out.print("Digite o novo valor da coluna: ");
                                    String atualizacao = teclado.nextLine();

                                    usuario.atualizar(cpfAtualizar, coluna, atualizacao);
                                    break;

                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                        }
                        break;


                    case 4:
                        while(true){
                            System.out.println("\n--- Tabela Vínculo ---");
                            System.out.println("O que deseja fazer?");
                            System.out.println("1- Listar vínculos");
                            System.out.println("2- Inserir vínculo");
                            System.out.println("3- Remover vínculo");
                            System.out.println("4- Atualizar vínculo");
                            System.out.println("0- Voltar");
                            System.out.print("Escolha uma opção: ");

                            int op = teclado.nextInt();
                            teclado.nextLine();

                            if(op == 0) break;

                            switch (op){
                                case 1:
                                    vinculo.listar();
                                    break;
                                
                                case 2:
                                    System.out.print("Matricula do estudante: ");
                                    String matricula = teclado.nextLine();
                                    
                                    System.out.print("Numero do curso: ");
                                    int cursoVinc = teclado.nextInt();
                                    teclado.nextInt();
                                    
                                    System.out.print("Data de entrada (AAAA-MM-DD): ");
                                    String dataEnt = teclado.nextLine();
                                    
                                    System.out.print("Status (Ativo, Cancelada, Formando, Graduado): ");
                                    String status = teclado.nextLine();
                                    
                                    System.out.print("Data de saida (AAAA-MM-DD): ");
                                    String dataSai = teclado.nextLine();
                                    
                                    vinculo.inserir(matricula, cursoVinc, dataEnt, status, dataSai);
                                    break;

                                case 3:
                                    System.out.print("Digite o id do vínculo a ser removido: ");
                                    int idRemover = teclado.nextInt();
                                    teclado.nextLine();

                                    vinculo.remover(idRemover);
                                    break;

                                case 4:
                                    System.out.print("Digite o id do vínculo a ser atualizado: ");
                                    int idAtualizar = teclado.nextInt();
                                    teclado.nextLine();

                                    System.out.print("Digite a coluna a ser atualizada: ");
                                    String coluna = teclado.nextLine();
                                    
                                    System.out.print("Digite o novo valor da coluna: ");
                                    String atualizacao = teclado.nextLine();

                                    vinculo.atualizar(idAtualizar, coluna, atualizacao);
                                    break;

                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                        }
                        break;
                    
                    
                    case 0:
                        System.out.println("\nEncerrando a aplicação. Até logo!");
                        break;

                    default:
                        System.out.println("\n[ERRO] Opção inválida! Tente novamente.");
                        break;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro crítico no banco de dados:");
            e.printStackTrace();
        } finally {
            teclado.close(); // Fecha o leitor do teclado de forma segura
        }
    }
}