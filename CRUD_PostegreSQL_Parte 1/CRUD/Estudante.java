import java.sql.*;

public class Estudante {
    private Connection conexao;

    public Estudante(Connection conexao){
        this.conexao = conexao;
    }


    public void inserir(String matricula, long cpf, double mc, int anoIngresso) throws SQLException{
        String sql = "INSERT INTO universidade.estudante (mat_estudante, cpf, MC, ano_ingresso) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.setLong(2, cpf);
            stmt.setDouble(3, mc);
            stmt.setInt(4, anoIngresso);
            
            stmt.executeUpdate();
            System.out.println("Estudante Inserido com sucesso!");
        }
    }


    public void listar() throws SQLException{
        String sql = "SELECT mat_estudante, cpf, MC, ano_ingresso FROM universidade.estudante";
        
        try (Statement stmt = conexao.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- LISTA DE ESTUDANTES CADASTRADOS ---");
            while (rs.next()) {
                System.out.println("Matrícla: " + rs.getString("mat_estudante") + 
                                   " | CPF: " + rs.getLong("cpf") + 
                                   " | MC: " + rs.getDouble("MC") + 
                                   " | Ano de ingresso: " + rs.getInt("ano_ingresso"));
            }
        }
    }


    public void atualizar(String matricula, String coluna, String atualizacao) throws SQLException{
        String sql = "UPDATE universidade.estudante SET " + coluna + " = ? WHERE mat_estudante = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            // CORREÇÃO: Identifica a coluna escolhida e faz a conversão correta do tipo
            if (coluna.equalsIgnoreCase("cpf")) {
                stmt.setLong(1, Long.parseLong(atualizacao)); // Converte o texto para Long
            } else if (coluna.equalsIgnoreCase("MC")) {
                stmt.setDouble(1, Double.parseDouble(atualizacao)); // Converte o texto para Double
            } else if (coluna.equalsIgnoreCase("ano_ingresso")) {
                stmt.setInt(1, Integer.parseInt(atualizacao)); // Converte o texto para Int
            } else {
                // Se for a própria matrícula ou outro campo de texto (caso exista)
                stmt.setString(1, atualizacao); 
            }
            
            stmt.setString(2, matricula);

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println("Coluna '" + coluna + "' atualizada com sucesso para o estudante com matricula " + matricula + "!");
            } else {
                System.out.println("Nenhum estudante encontrado com a matricula " + matricula + ".");
            }
        }
    }


    public void remover(String matricula) throws SQLException{
        String sql = "DELETE FROM universidade.estudante WHERE mat_estudante = ?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, matricula);

            int linhasAfetadas = stmt.executeUpdate();
        
            if (linhasAfetadas > 0) {
                System.out.println("Estudante com matrícula " + matricula + " removido com sucesso!");
            } else {
                System.out.println("Nenhum estudante foi encontrado com a matrícula " + matricula + ".");
            }
        }
    }
}
