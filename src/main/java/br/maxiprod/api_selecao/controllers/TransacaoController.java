package br.maxiprod.api_selecao.controllers;

import br.maxiprod.api_selecao.dto.TransacaoDTOget;
import br.maxiprod.api_selecao.dto.TransacaoDTOpost;
import br.maxiprod.api_selecao.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transacoes")
@Tag(
        name = "API Gastos",
        description = "API responsável pelos serviços da entidade Pessoa no sistema."
)
public class TransacaoController {

    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @Operation(
            summary = "Cria Transações",
            description = "Cria uma nova transação na base a partir do dto de entrada da mesma."
    )
    public ResponseEntity<TransacaoDTOget> postTransacao(@RequestBody TransacaoDTOpost dto){
        TransacaoDTOget retorno = transacaoService.novaTransacao(dto);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar Transação",
            description = "Atualiza uma transação pelo id."
    )
    public ResponseEntity<TransacaoDTOget> putTransacao(@RequestBody TransacaoDTOpost dto, @PathVariable Long id){
        TransacaoDTOget retorno = transacaoService.atualizarTransacao(dto, id);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Pegar transação por id",
            description = "Pega uma transação convertida para o dto da mesma."
    )
    public ResponseEntity<TransacaoDTOget> getTransacao(@PathVariable Long id){
        TransacaoDTOget retorno = transacaoService.pegarTransacaoPorId(id);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Pegar Transações",
            description = "Pega uma lista de transações convertidas para o dto da mesma."
    )
    public ResponseEntity<List<TransacaoDTOget>> getTransacoes(){
        List<TransacaoDTOget> retorno = transacaoService.pegarTransacoes();

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @Operation(
            summary = "Deletar transação",
            description = "Pega um objeto transação pelo id, e o 'deleta' no modelo lógico."
    )
    public ResponseEntity<String> deleteTranscao(@PathVariable Long id){
        String retorno = transacaoService.deletarTransacao(id);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
}
