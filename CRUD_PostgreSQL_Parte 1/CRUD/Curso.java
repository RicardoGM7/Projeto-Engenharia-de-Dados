import java.sql.*;

public class Curso {
    private Connection conexao;

    // Construtor que recebe a conexão global criada no Principal.java
    public Curso(Connection conexao) {
        this.conexao = conexao;
    }

    // Método correto recebendo os parâmetros de texto que vêm do Menu
    public void inserir(String nome, String grau, String turno, String campus, String nivel) throws SQLException{
        String sql = "INSERT INTO universidade.curso (nome, grau, turno, campus, nivel) VALUES (?, ?::universidade.tipo_grau, ?::universidade.tipo_turno, ?, ?::universidade.tipo_nivel)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, grau);   // Ex: 'Bacharelado'
            stmt.setString(3, turno);  // Ex: 'Vespertino'
            stmt.setString(4, campus); // Ex: 'São Cristóvão'
            stmt.setString(5, nivel);  // Ex: 'Graduação'
            
            stmt.executeUpdate();
            System.out.println("Curso inserido com sucesso!");
        }
    }

    // Método de listagem que o arquivo Principal.java estava tentando chamar
    public void listar() throws SQLException{
        String sql = "SELECT idCurso, nome, turno, campus FROM universidade.curso";
        
        try (Statement stmt = conexao.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- LISTA DE CURSOS CADASTRADOS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idCurso") + 
                                   " | Nome: " + rs.getString("nome") + 
                                   " | Turno: " + rs.getString("turno") + 
                                   " | Campus: " + rs.getString("campus"));
            }
        }
    }


    public void atualizar(int id, String coluna, String atualizacao) throws SQLException{
        String sql = "UPDATE universidade.curso SET " + coluna + " = ? WHERE idCurso = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, atualizacao);
            stmt.setInt(2, id);

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println("Coluna '" + coluna + "' atualizada com sucesso para o curso id " + id + "!");
            } else {
                System.out.println("Nenhum curso encontrado com o id " + id + ".");
            }
        }
    }


    public void remover(int id) throws SQLException{
        String sql = "DELETE FROM universidade.curso WHERE idCurso = ?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
        
            if (linhasAfetadas > 0) {
                System.out.println("Curso com id " + id + " removido com sucesso!");
            } else {
                System.out.println("Nenhum curso foi encontrado com o id " + id + ".");
            }
        }
    }
}