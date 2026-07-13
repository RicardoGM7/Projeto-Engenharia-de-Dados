package com.projetomongo.model;

import java.math.BigDecimal;

public class Estudante {
    private String matEstudante;
    private Long cpf;
    private BigDecimal mc;
    private Integer anoIngresso;

    // Construtor completo
    public Estudante(String matEstudante, Long cpf, BigDecimal mc, Integer anoIngresso) {
        this.matEstudante = matEstudante;
        this.cpf = cpf;
        this.mc = mc;
        this.anoIngresso = anoIngresso;
    }

    // Getters
    public String getMatEstudante() {
        return matEstudante;
    }

    public Long getCpf() {
        return cpf;
    }

    public BigDecimal getMc() {
        return mc;
    }

    public Integer getAnoIngresso() {
        return anoIngresso;
    }
}