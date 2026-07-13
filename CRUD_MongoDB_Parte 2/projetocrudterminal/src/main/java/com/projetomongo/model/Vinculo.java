package com.projetomongo.model;

import java.time.LocalDate;

public class Vinculo {
    private Integer idVinculo;
    private String matEstudante;
    private Integer curso;
    private LocalDate dataEntrada;
    private StatusEnum status;
    private LocalDate dataSaida;

    // Construtor completo
    public Vinculo(Integer idVinculo, String matEstudante, Integer curso, LocalDate dataEntrada, StatusEnum status,
            LocalDate dataSaida) {
        this.idVinculo = idVinculo;
        this.matEstudante = matEstudante;
        this.curso = curso;
        this.dataEntrada = dataEntrada;
        this.status = status;
        this.dataSaida = dataSaida;
    }

    // Getters
    public Integer getIdVinculo() {
        return idVinculo;
    }

    public String getMatEstudante() {
        return matEstudante;
    }

    public Integer getCurso() {
        return curso;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }
}