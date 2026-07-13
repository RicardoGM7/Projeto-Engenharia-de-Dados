package com.projetomongo.repository;

import com.projetomongo.model.Usuario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private MongoCollection<Document> collection;

    // O construtor recebe a coleção "usuario" do banco
    public UsuarioRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // C - CREATE (Inserir Usuário)
    public void inserir(Usuario usuario) {
        try {

            Document doc = new Document()
                    .append("cpf", usuario.getCpf()) // Adicionamos o campo 'cpf' para agradar o schema
                    .append("nome", usuario.getNome())
                    .append("data_nascimento", usuario.getDataNascimento())
                    .append("email", usuario.getEmails())
                    .append("telefone", usuario.getTelefones())
                    .append("login", usuario.getLogin())
                    .append("senha", usuario.getSenha());

            collection.insertOne(doc);

        } catch (Exception e) {
            System.out.println("Erro ao formatar a data ou inserir: " + e.getMessage());
        }
    }

    // R - READ (Listar todos)
    public List<Document> listarTodos() {
        List<Document> lista = new ArrayList<>();
        for (Document doc : collection.find()) {
            lista.add(doc);
        }
        return lista;
    }

    // U - UPDATE (Atualizar login e senha buscando pelo CPF)
    public void atualizarCredenciais(Long cpf, String novoLogin, String novaSenha) {
        collection.updateOne(
                Filters.eq("cpf", cpf),
                new Document("$set", new Document("login", novoLogin).append("senha", novaSenha)));
    }

    // D - DELETE (Deletar usuário pelo CPF)
    public void deletar(Long cpf) {
        collection.deleteOne(Filters.eq("cpf", cpf));
    }
}