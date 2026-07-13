package com.projetomongo.model;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
    private Long cpf;
    private String nome;
    private LocalDate dataNascimento;
    private List<String> emails;
    private List<String> telefones;
    private String login;
    private String senha;

    // Construtor completo
    public Usuario(Long cpf, String nome, LocalDate dataNascimento, List<String> emails, List<String> telefones,
            String login, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.emails = emails;
        this.telefones = telefones;
        this.login = login;
        this.senha = senha;
    }

    // Getters
    public Long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<String> getEmails() {
        return emails;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}