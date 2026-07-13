import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vinculo {
    private Connection conexao;

    public Vinculo(Connection conexao) {
        this.conexao = conexao;
    }


    public void inserir(String matricula, int curso, String dataEntrada, String status, String dataSaida) throws SQLException {
        String sql = "INSERT INTO universidade.vinculo (mat_estudante, curso, data_entrada, status, data_saida) VALUES (?, ?, ?::date, ?::universidade.status_estudante, ?::date)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.setInt(2, curso);
            stmt.setString(3, dataEntrada);
            stmt.setString(4, status);
            stmt.setString(5, dataSaida);
            stmt.executeUpdate();
            System.out.println("Vínculo inserido com sucesso!");
        }
    }
    

    public void listar() throws SQLException{
        String sql = "SELECT idVinculo, mat_estudante, curso, data_entrada, status, data_saida FROM universidade.vinculo";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql); 
         ResultSet rs = stmt.executeQuery()) {
        
            System.out.println("\n--- LISTA DE VÍNCULOS ---");
            while (rs.next()) {
                
                // Tratamento preventivo: se o status ou as datas vierem com formatos estritos,
                // o .getObject().toString() ou .getString() lê de forma limpa.
                int id = rs.getInt("idVinculo");
                String matricula = rs.getString("mat_estudante");
                int idCurso = rs.getInt("curso");
                String dataEntrada = rs.getString("data_entrada");
                
                // Como 'status' é um ENUM no Postgres, o getObject().toString() evita erros de conversão
                String status = rs.getObject("status") != null ? rs.getObject("status").toString() : "N/A";
                
                // data_saida pode ser nula, o getString lida bem, mas podemos garantir com uma validação
                String dataSaida = rs.getString("data_saida") != null ? rs.getString("data_saida") : "Ativo";

                System.out.println("Id: " + id + 
                                " | Matricula: " + matricula + 
                                " | Curso (ID): " + idCurso + 
                                " | Entrada: " + dataEntrada +
                                " | Status: " + status +
                                " | Saída: " + dataSaida);
            }
        }       
    }


    public void atualizar(long id, String coluna, String atualizacao) throws SQLException{
        String sql = "UPDATE universidade.vinculo SET " + coluna + " = ? WHERE idVinculo = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            // CORREÇÃO: Tratamento dinâmico de tipos para evitar quebras na AWS
            if (coluna.equalsIgnoreCase("curso")) {
                stmt.setInt(1, Integer.parseInt(atualizacao));
            } else if (coluna.equalsIgnoreCase("data_entrada") || coluna.equalsIgnoreCase("data_saida")) {
                stmt.setDate(1, java.sql.Date.valueOf(atualizacao));
            } else {
                stmt.setString(1, atualizacao);
            }
            
            stmt.setLong(2, id);

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println("Coluna '" + coluna + "' atualizada com sucesso para o vínculo com id " + id + "!");
            } else {
                System.out.println("Nenhum vínculo encontrado com o id " + id + ".");
            }
        }
    }


    public void remover(int id) throws SQLException{
        String sql = "DELETE FROM universidade.vinculo WHERE idvinculo = ?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();
        
            if (linhasAfetadas > 0) {
                System.out.println("Vínculo com o id " + id + " removido com sucesso!");
            } else {
                System.out.println("Nenhum vínculo foi encontrado com o id " + id + ".");
            }
        }
    }
}
