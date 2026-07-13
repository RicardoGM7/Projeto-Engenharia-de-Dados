package com.projetomongo;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import com.projetomongo.model.*;
import com.projetomongo.repository.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static LocalDate lerDataOpcional(String mensagem) {
        System.out.print(mensagem);
        String texto = sc.nextLine().trim();
        if (texto.isEmpty()) {
            return null;
        }
        return LocalDate.parse(texto, fmt1);
    }

    public static void main(String[] args) {
        sc.useLocale(Locale.US);

        String uri = "mongodb://professor:professor@44.215.176.152:27017/Etapa2?authSource=admin";
        String nomeBanco = "Etapa2";

        System.out.println("Conectando ao MongoDB...");

        try (MongoClient mongoClient = MongoClients.create(uri);) {
            MongoDatabase database = mongoClient.getDatabase(nomeBanco);

            database.runCommand(new Document("ping", 1));

            System.out.println("Conectado com sucesso ao banco: " + database.getName());

            // Inicializando os repositórios com suas respectivas coleções
            UsuarioRepository usuarioRepo = new UsuarioRepository(database.getCollection("usuario"));
            EstudanteRepository estudanteRepo = new EstudanteRepository(database.getCollection("estudante"));
            CursoRepository cursoRepo = new CursoRepository(database.getCollection("curso"));
            VinculoRepository vinculoRepo = new VinculoRepository(database.getCollection("vinculo"));

            int opcaoPrincipal = 0;

            while (opcaoPrincipal != 5) {
                System.out.println("\n=================================");
                System.out.println("     SISTEMA UNIVERSITÁRIO      ");
                System.out.println("=================================");
                System.out.println("1 - Gerenciar Usuários");
                System.out.println("2 - Gerenciar Estudantes");
                System.out.println("3 - Gerenciar Cursos");
                System.out.println("4 - Gerenciar Vínculos");
                System.out.println("5 - Sair");
                System.out.print("Escolha uma opção: ");
                opcaoPrincipal = sc.nextInt();
                sc.nextLine(); // Limpa o buffer

                switch (opcaoPrincipal) {
                    case 1:
                        menuUsuario(usuarioRepo);
                        break;
                    case 2:
                        menuEstudante(estudanteRepo);
                        break;
                    case 3:
                        menuCurso(cursoRepo);
                        break;
                    case 4:
                        menuVinculo(vinculoRepo);
                        break;
                    case 5:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Você digitou letras onde deveria ser um número! Use apenas ponto e números.");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Tipo de dado incompatível na digitação.");

        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data inválido! Lembre-se de usar dd/MM/yyyy.");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Esse valor não existe nas opções permitidas. Verifique a ortografia.");

        } catch (MongoWriteException e) {
            System.out.println(
                    "Erro no Banco de Dados: O documento não passou nas regras de validação do MongoDB. Verifique os limites de tamanho e campos obrigatórios.");

        } catch (com.mongodb.MongoTimeoutException e) {
            System.out.println(
                    "Erro no Banco de Dados: O tempo de conexão esgotou. O servidor do MongoDB na AWS parece estar desligado ou inacessível.");

        } catch (com.mongodb.MongoSocketException e) {
            System.out.println(
                    "Erro no Banco de Dados: Falha na rede ao tentar comunicar com o servidor do banco de dados.");

        } catch (Exception e) {
            // O Exception genérico continua aqui no final, como rede de segurança!
            System.err.println("Erro crítico no sistema: " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            sc.close();
        }
    }

    // ================= SUBMENU USUÁRIO =================
    private static void menuUsuario(UsuarioRepository repo) {
        int op = 0;
        while (op != 5) {
            System.out.println("\n--- MENU USUÁRIO ---");
            System.out.println("1 - Inserir Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Atualizar Login/Senha");
            System.out.println("4 - Deletar Usuário");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("CPF (Apenas números): ");
                Long cpf = sc.nextLong();
                sc.nextLine();
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Data Nascimento (DD/MM/AAAA): ");
                LocalDate data = LocalDate.parse(sc.nextLine(), fmt1);
                System.out.print("Emails (separados por vírgula): ");
                String emailsRaw = sc.nextLine();
                System.out.print("Telefones (separados por vírgula): ");
                String telsRaw = sc.nextLine();
                System.out.print("Login: ");
                String login = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();

                List<String> emails = Arrays.asList(emailsRaw.split(",\\s*"));
                List<String> tels = Arrays.asList(telsRaw.split(",\\s*"));

                repo.inserir(new Usuario(cpf, nome, data, emails, tels, login, senha));
                System.out.println("Usuário inserido!");
            } else if (op == 2) {
                for (Document d : repo.listarTodos())
                    System.out.println(d.toJson());
            } else if (op == 3) {
                System.out.print("CPF do usuário: ");
                Long cpf = sc.nextLong();
                sc.nextLine();
                System.out.print("Novo Login: ");
                String login = sc.nextLine();
                System.out.print("Nova Senha: ");
                String senha = sc.nextLine();
                repo.atualizarCredenciais(cpf, login, senha);
                System.out.println("Credenciais atualizadas!");
            } else if (op == 4) {
                System.out.print("CPF do usuário a deletar: ");
                Long cpf = sc.nextLong();
                sc.nextLine();
                repo.deletar(cpf);
                System.out.println("Usuário deletado!");
            }
        }
    }

    // ================= SUBMENU ESTUDANTE =================
    private static void menuEstudante(EstudanteRepository repo) {
        int op = 0;
        while (op != 5) {
            System.out.println("\n--- MENU ESTUDANTE ---");
            System.out.println("1 - Inserir Estudante");
            System.out.println("2 - Listar Estudantes");
            System.out.println("3 - Atualizar MC");
            System.out.println("4 - Deletar Estudante");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("Matrícula (max 7 caracteres): ");
                String mat = sc.nextLine();
                System.out.print("CPF do Usuário vinculado: ");
                Long cpf = sc.nextLong();
                System.out.print("MC (Ex: 8.5): ");
                BigDecimal mc = sc.nextBigDecimal();
                System.out.print("Ano Ingresso: ");
                Integer ano = sc.nextInt();
                sc.nextLine();

                repo.inserir(new Estudante(mat, cpf, mc, ano));
                System.out.println("Estudante inserido!");
            } else if (op == 2) {
                for (Document d : repo.listarTodos())
                    System.out.println(d.toJson());
            } else if (op == 3) {
                System.out.print("Matrícula do estudante: ");
                String mat = sc.nextLine();
                System.out.print("Novo MC: ");
                BigDecimal mc = sc.nextBigDecimal();
                sc.nextLine();
                repo.atualizarMC(mat, mc);
                System.out.println("MC atualizado!");
            } else if (op == 4) {
                System.out.print("Matrícula a deletar: ");
                String mat = sc.nextLine();
                repo.deletar(mat);
                System.out.println("Estudante deletado!");
            }
        }
    }

    // ================= SUBMENU CURSO =================
    private static void menuCurso(CursoRepository repo) {
        int op = 0;
        while (op != 5) {
            System.out.println("\n--- MENU CURSO ---");
            System.out.println("1 - Inserir Curso");
            System.out.println("2 - Listar Cursos");
            System.out.println("3 - Atualizar Turno/Campus");
            System.out.println("4 - Deletar Curso");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) { // inserir curso
                System.out.print("ID do Curso (Inteiro): ");
                Integer id = sc.nextInt();
                sc.nextLine();
                System.out.print("Nome do Curso: ");
                String nome = sc.nextLine();
                System.out.print("Grau (Ex: Bacharelado): ");
                String grau = sc.nextLine();
                System.out.print("Turno (Matutino, Vespertino, Noturno, Integral): ");
                TurnoEnum turno = TurnoEnum.valueOf(sc.nextLine());
                System.out.print("Campus: ");
                String campus = sc.nextLine();
                System.out.print("Nível (Graduacao, Mestrado, Doutorado, Lato): ");
                NivelEnum nivel = NivelEnum.valueOf(sc.nextLine());

                repo.inserir(new Curso(id, nome, grau, turno, campus, nivel));
                System.out.println("Curso inserido!");
            } else if (op == 2) { // listar curso
                for (Document d : repo.listarTodos())
                    System.out.println(d.toJson());
            } else if (op == 3) { // atualizar currso
                System.out.print("ID do Curso: ");
                Integer id = sc.nextInt();
                sc.nextLine();
                System.out.print("Novo Turno: ");
                TurnoEnum turno = TurnoEnum.valueOf(sc.nextLine());
                System.out.print("Novo Campus: ");
                String campus = sc.nextLine();
                repo.atualizarTurnoECampus(id, turno, campus);
                System.out.println("Curso atualizado!");
            } else if (op == 4) { // Deletar curso
                System.out.print("ID do Curso a deletar: ");
                Integer id = sc.nextInt();
                sc.nextLine();
                repo.deletar(id);
                System.out.println("Curso deletado!");
            }
        }
    }

    // ================= SUBMENU VÍNCULO =================
    private static void menuVinculo(VinculoRepository repo) {
        int op = 0;
        while (op != 5) {
            System.out.println("\n--- MENU VÍNCULO ---");
            System.out.println("1 - Inserir Vínculo");
            System.out.println("2 - Listar Vínculos");
            System.out.println("3 - Atualizar Status/Saída");
            System.out.println("4 - Deletar Vínculo");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("ID do Vínculo (Inteiro): ");
                Integer id = sc.nextInt();
                sc.nextLine();
                System.out.print("Matrícula do Estudante(max 7 caracteres): ");
                String mat = sc.nextLine();
                System.out.print("ID do Curso: ");
                Integer idCurso = sc.nextInt();
                sc.nextLine();
                System.out.print("Data Nascimento (DD/MM/AAAA): ");
                LocalDate entrada = LocalDate.parse(sc.nextLine(), fmt1);
                System.out.print("Status(Ativo, Cancelada, Formando, Graduado): ");
                StatusEnum status = StatusEnum.valueOf(sc.nextLine());
                LocalDate saida = lerDataOpcional("Data Saída (DD/MM/YYYY ou Enter para vazio): ");

                repo.inserir(new Vinculo(id, mat, idCurso, entrada, status, saida));
                System.out.println("Vínculo inserido!");
            } else if (op == 2) {
                for (Document d : repo.listarTodos())
                    System.out.println(d.toJson());
            } else if (op == 3) {
                System.out.print("ID do Vínculo: ");
                Integer id = sc.nextInt();
                sc.nextLine();
                System.out.print("Novo Status: ");
                StatusEnum status = StatusEnum.valueOf(sc.nextLine());
                LocalDate saida = lerDataOpcional("Nova Data de Saída (DD/MM/YYYY ou Enter para vazio): ");
                repo.atualizarStatusESaida(id, status, saida);
                System.out.println("Vínculo atualizado!");
            } else if (op == 4) {
                System.out.print("ID do Vínculo a deletar: ");
                Integer id = sc.nextInt();
                sc.nextLine();
                repo.deletar(id);
                System.out.println("Vínculo deletado!");
            }
        }
    }
}