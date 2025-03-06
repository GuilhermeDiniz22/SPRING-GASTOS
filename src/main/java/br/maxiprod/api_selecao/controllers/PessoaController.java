package br.maxiprod.api_selecao.controllers;

import br.maxiprod.api_selecao.dto.ConsultaDTO;
import br.maxiprod.api_selecao.dto.PessoaDTO;
import br.maxiprod.api_selecao.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoas")
@Tag(
        name = "API Gastos",
        description = "API responsável pelos serviços da entidade Pessoa no sistema."
)
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @Operation(
            summary = "Postar nova pessoa",
            description = "Posta uma nova pessoa no banco de dados a partir do dto."
    )
    public ResponseEntity<PessoaDTO> postPessoa(@RequestBody PessoaDTO dto){
        PessoaDTO retorno = pessoaService.novaPessoa(dto);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar pessoa",
            description = "Atualiza uma pessoa , a partir do id informado."
    )
    public ResponseEntity<PessoaDTO> putPessoa(@RequestBody PessoaDTO dto, @PathVariable Long id){
        PessoaDTO retorno = pessoaService.atualizarPessoa(dto, id);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @Operation(
            summary = "Pegar pessoa",
            description = "Get uma pessoa , a partir do id informado."
    )
    public ResponseEntity<PessoaDTO> getPessoa(@PathVariable Long id){
        PessoaDTO retorno = pessoaService.pegarPessoaPorId(id);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }


    @GetMapping
    @Operation(
            summary = "Pegar pessoas",
            description = "Pega uma lista de pessoas da base convertidas para a lista de PessoaDTO."
    )
    public ResponseEntity<List<PessoaDTO>> getPessoas(){
        List<PessoaDTO> retorno = pessoaService.pegarPessoas();

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping("consulta-total")
    @Operation(
            summary = "Pegar Consulta",
            description = "Pega um objeto consulta que contem a lista de pessoas da base, junto com total de receitas , despesas e o saldo total."
    )
    public ResponseEntity<ConsultaDTO> getConsultaTotal(){
        ConsultaDTO retorno = pessoaService.consultarPessoas();

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }


    @GetMapping("consulta-total/{id}")
    @Operation(
            summary = "Pegar Consulta",
            description = "Pega um objeto consulta que contem a pessoa da base pelo id, junto com total de receitas , despesas e o saldo total da mesma."
    )
    public ResponseEntity<ConsultaDTO> getConsultaTotalPorPessoa(@PathVariable Long id){
        ConsultaDTO retorno = pessoaService.consultarPessoaPorId(id);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Deletar pessoa",
            description = "Modelo de deleção lógica, onde o objeto é 'deletado' da consulta da base , porém seus dados permnecem no banco, os dados das transacoes tb são 'deletados'."
    )
    public ResponseEntity<String> deletePessoa(@PathVariable Long id){
        String retorno = pessoaService.deletarPessoaEtransacoes(id);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
}



