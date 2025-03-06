package br.maxiprod.api_selecao.dto;

import jakarta.persistence.Column;

import java.util.Objects;

public class PessoaDTO {

    private Long id;

    private String nome;

    private int idade;

    public PessoaDTO(Long id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaDTO pessoaDTO = (PessoaDTO) o;
        return idade == pessoaDTO.idade && Objects.equals(nome, pessoaDTO.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
