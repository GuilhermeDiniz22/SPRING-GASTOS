package br.maxiprod.api_selecao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultaDTO {
    @JsonProperty("pessoas")
    private List<?> pessoas;

    @JsonProperty("pessoa")
    private PessoaDTO pessoa;

    private BigDecimal totalReceita;

    private BigDecimal totalDespesa;

    private BigDecimal saldoTotal;

    public ConsultaDTO(){}

    public ConsultaDTO(List<?> pessoas, BigDecimal totalReceita, BigDecimal totalDespesa, BigDecimal saldoTotal) {
        this.pessoas = pessoas;
        this.totalReceita = totalReceita;
        this.totalDespesa = totalDespesa;
        this.saldoTotal = saldoTotal;
    }

    public ConsultaDTO(PessoaDTO pessoa, BigDecimal totalReceita, BigDecimal totalDespesa, BigDecimal saldoTotal) {
        this.pessoa = pessoa;
        this.totalReceita = totalReceita;
        this.totalDespesa = totalDespesa;
        this.saldoTotal = saldoTotal;
    }

    public List<?> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<?> pessoas) {
        this.pessoas = pessoas;
    }

    public BigDecimal getTotalReceita() {
        return totalReceita;
    }

    public void setTotalReceita(BigDecimal totalReceita) {
        this.totalReceita = totalReceita;
    }

    public BigDecimal getTotalDespesa() {
        return totalDespesa;
    }

    public void setTotalDespesa(BigDecimal totalDespesa) {
        this.totalDespesa = totalDespesa;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }
}
