package com.projetomongo.repository;

import com.projetomongo.model.Curso;
import com.projetomongo.model.TurnoEnum;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    private MongoCollection<Document> collection;

    // O construtor recebe a coleção "curso" do banco
    public CursoRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // C - CREATE (Inserir Curso)
    public void inserir(Curso curso) {
        Document doc = new Document()
                .append("_id", curso.getIdCurso()) // Usando o idCurso como a chave primária física (_id)
                .append("nome", curso.getNome())
                .append("grau", curso.getGrau())
                .append("turno", curso.getTurno())
                .append("campus", curso.getCampus())
                .append("nivel", curso.getNivel());

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

    // U - UPDATE (Atualizar o turno e o campus buscando pelo ID)
    public void atualizarTurnoECampus(Integer idCurso, TurnoEnum novoTurno, String novoCampus) {
        collection.updateOne(
                Filters.eq("_id", idCurso),
                new Document("$set", new Document("turno", novoTurno).append("campus", novoCampus)));
    }

    // D - DELETE (Deletar curso pelo ID)
    public void deletar(Integer idCurso) {
        collection.deleteOne(Filters.eq("_id", idCurso));
    }
}