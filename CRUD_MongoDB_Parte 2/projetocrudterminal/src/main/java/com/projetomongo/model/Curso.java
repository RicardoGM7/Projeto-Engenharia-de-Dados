package com.projetomongo.model;

public class Curso {
    private Integer idCurso;
    private String nome;
    private String grau;
    private TurnoEnum turno;
    private String campus;
    private NivelEnum nivel;

    // Construtor completo
    public Curso(Integer idCurso, String nome, String grau, TurnoEnum turno, String campus, NivelEnum nivel) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.grau = grau;
        this.turno = turno;
        this.campus = campus;
        this.nivel = nivel;
    }

    // Getters
    public Integer getIdCurso() {
        return idCurso;
    }

    public String getNome() {
        return nome;
    }

    public String getGrau() {
        return grau;
    }

    public TurnoEnum getTurno() {
        return turno;
    }

    public String getCampus() {
        return campus;
    }

    public NivelEnum getNivel() {
        return nivel;
    }
}