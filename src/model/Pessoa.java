package model;

import java.time.LocalDate;

public abstract class Pessoa {
    protected final String nome;
    protected final LocalDate dataNascimento;

    protected Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
