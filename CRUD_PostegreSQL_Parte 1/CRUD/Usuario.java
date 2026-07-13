import java.sql.*;

public class Usuario{
    private Connection conexao;

    public Usuario(Connection conexao) {
        this.conexao = conexao;
    }


    public void inserir(long cpf, String nome, String dataNasc, String email, String tel, String login, String senha) throws SQLException {
        String sql = "INSERT INTO universidade.usuario (cpf, nome, data_nascimento, email, telefone, login, senha) VALUES (?, ?, ?::date, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, dataNasc);
            stmt.setArray(4, conexao.createArrayOf("varchar", new String[]{email}));
            stmt.setArray(5, conexao.createArrayOf("varchar", new String[]{tel}));
            stmt.setString(6, login);
            stmt.setString(7, senha);
            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        }
    }
    

    public void listar() throws SQLException{
        String sql = "SELECT cpf, nome, data_nascimento, email, telefone, login, senha FROM universidade.usuario";
        
        try (Statement stmt = conexao.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- LISTA DE USUÁRIOS CADASTRADOS ---");
            while (rs.next()) {
                System.out.println("CPF: " + rs.getLong("cpf") + 
                                   " | Nome: " + rs.getString("nome") + 
                                   " | Data de nascimento: " + rs.getString("data_nascimento") + 
                                   " | Email: " + rs.getString("email") +
                                   " | Telefone: " + rs.getString("telefone") +
                                   " | Login :" + rs.getString("login") +
                                   " | Senha :" + rs.getString("senha"));
            }
        }
    }


    public void atualizar(long cpf, String coluna, String atualizacao) throws SQLException{
        String sql = "UPDATE universidade.usuario SET " + coluna + " = ? WHERE cpf = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            // CORREÇÃO: Identifica a coluna e faz o mapeamento correto do tipo para o Postgres
            if (coluna.equalsIgnoreCase("data_nascimento")) {
                // Converte a string "AAAA-MM-DD" no tipo Date do SQL
                stmt.setDate(1, java.sql.Date.valueOf(atualizacao));
                
            } else if (coluna.equalsIgnoreCase("email") || coluna.equalsIgnoreCase("telefone")) {
                // Transforma a nova string em um array de um elemento, igual você fez no inserir
                stmt.setArray(1, conexao.createArrayOf("varchar", new String[]{atualizacao}));
                
            } else {
                // Para nome, login e senha, continua enviando como texto normal
                stmt.setString(1, atualizacao);
            }
            
            stmt.setLong(2, cpf);

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println("Coluna '" + coluna + "' atualizada com sucesso para o usuario com CPF " + cpf + "!");
            } else {
                System.out.println("Nenhum usuário encontrado com o CPF " + cpf + ".");
            }
        }
    }


    public void remover(long cpf) throws SQLException{
        String sql = "DELETE FROM universidade.usuario WHERE cpf = ?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setLong(1, cpf);

            int linhasAfetadas = stmt.executeUpdate();
        
            if (linhasAfetadas > 0) {
                System.out.println("Usuário com CPF " + cpf + " removido com sucesso!");
            } else {
                System.out.println("Nenhum usuário foi encontrado com o CPF " + cpf + ".");
            }
        }
    }
}