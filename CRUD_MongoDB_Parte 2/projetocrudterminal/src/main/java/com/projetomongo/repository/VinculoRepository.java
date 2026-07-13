package com.projetomongo.repository;

import com.projetomongo.model.StatusEnum;
import com.projetomongo.model.Vinculo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VinculoRepository {
    private MongoCollection<Document> collection;

    // O construtor recebe a coleção "vinculo" do banco
    public VinculoRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // C - CREATE (Inserir Vínculo)
    public void inserir(Vinculo vinculo) {
        Document doc = new Document()
                .append("_id", vinculo.getIdVinculo()) // idVinculo como chave primária física
                .append("mat_estudante", vinculo.getMatEstudante())
                .append("curso", vinculo.getCurso())
                .append("data_entrada", vinculo.getDataEntrada())
                .append("status", vinculo.getStatus());
        if (vinculo.getDataSaida() != null) {
            doc.append("data_saida", vinculo.getDataSaida());
        }

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

    // U - UPDATE (Atualizar o status e a data de saída buscando pelo ID do vínculo)
    public void atualizarStatusESaida(Integer idVinculo, StatusEnum novoStatus, LocalDate novaDataSaida) {
        collection.updateOne(
                Filters.eq("_id", idVinculo),
                new Document("$set", new Document("status", novoStatus).append("data_saida", novaDataSaida)));
    }

    // D - DELETE (Deletar vínculo pelo ID)
    public void deletar(Integer idVinculo) {
        collection.deleteOne(Filters.eq("_id", idVinculo));
    }
}