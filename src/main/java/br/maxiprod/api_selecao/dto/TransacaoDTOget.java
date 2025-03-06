package br.maxiprod.api_selecao.dto;

import br.maxiprod.api_selecao.models.Pessoa;

import java.math.BigDecimal;

public class TransacaoDTOget {
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private String tipo;

    private Pessoa pessoa;

    public TransacaoDTOget(Long id, String descricao, BigDecimal valor, String tipo, Pessoa pessoa) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.pessoa = pessoa;
    }

    public TransacaoDTOget(Long id, String descricao, BigDecimal valor, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
