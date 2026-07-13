package com.projetomongo.repository;

import com.projetomongo.model.Estudante;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.Decimal128;

public class EstudanteRepository {
    private MongoCollection<Document> collection;

    public EstudanteRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // C - CREATE (Inserir Estudante)
    public void inserir(Estudante estudante) {
        Document doc = new Document()
                .append("mat_estudante", estudante.getMatEstudante()) // Matrícula como chave primária física
                .append("cpf", estudante.getCpf())
                .append("MC", new Decimal128(estudante.getMc())) // Salva como número no Mongo
                .append("ano_ingresso", estudante.getAnoIngresso());

        collection.insertOne(doc);
    }

    // R - READ (Listar todos)
    public List<Document> listarTodos() {
        List<Document> lista = new ArrayList<>();
        for (Document doc : collection.find()) {
            lista.add(doc);
        }
        return lista;
    }

    // U - UPDATE (Atualizar o MC buscando pela matrícula)
    public void atualizarMC(String matEstudante, BigDecimal novoMC) {
        collection.updateOne(
                Filters.eq("mat_estudante", matEstudante),
                new Document("$set", new Document("MC", new Decimal128(novoMC))));
    }

    // D - DELETE (Deletar estudante pela matrícula)
    public void deletar(String matEstudante) {
        collection.deleteOne(Filters.eq("mat_estudante", matEstudante));
    }
}