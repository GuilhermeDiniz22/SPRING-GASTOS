package br.maxiprod.api_selecao.service;

import br.maxiprod.api_selecao.dto.TransacaoDTOget;
import br.maxiprod.api_selecao.dto.TransacaoDTOpost;
import br.maxiprod.api_selecao.models.Pessoa;
import br.maxiprod.api_selecao.models.Transacao;
import br.maxiprod.api_selecao.repository.PessoaRepository;
import br.maxiprod.api_selecao.repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;

    private PessoaRepository pessoaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, PessoaRepository pessoaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public TransacaoDTOget novaTransacao(TransacaoDTOpost dto) {

        Pessoa p = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Pessoa com id " + dto.getPessoaId() + " não encontrada! "));

        Transacao t = new Transacao();
        t.setId(dto.getId());
        t.setDescricao(dto.getDescricao());

        t.setTipo(dto.getTipo().trim().toLowerCase());

        if (p.getIdade() < 18) {
            t.setTipo("despesa");
        }

        t.setValor(dto.getValor());
        t.setPessoa(p);
        t.setAtivo(Boolean.TRUE);

        Transacao retorno = transacaoRepository.save(t);

        p.addTransacao(retorno);

        return new TransacaoDTOget(retorno.getId(), retorno.getDescricao(), retorno.getValor(), retorno.getTipo(),
                retorno.getPessoa());

    }

    public TransacaoDTOget pegarTransacaoPorId(Long id) {
        Transacao t = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação com id " + id + " não encontrada! "));

        return new TransacaoDTOget(t.getId(), t.getDescricao(), t.getValor(), t.getTipo(), t.getPessoa());
    }

    public List<TransacaoDTOget> pegarTransacoes() {
        List<TransacaoDTOget> retorno = transacaoRepository.findAll()
                .stream()
                .map((t) -> new TransacaoDTOget(t.getId(), t.getDescricao(), t.getValor(), t.getTipo(), t.getPessoa()))
                .toList();

        return retorno;
    }

    @Transactional
    public TransacaoDTOget atualizarTransacao(TransacaoDTOpost dto, Long id) {
        Pessoa p = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Pessoa com id " + dto.getPessoaId() + " não encontrada! "));

        Transacao t = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação com id " + id +  "não encontrada! "));

        t.setId(dto.getId());
        t.setTipo(dto.getTipo());
        t.setValor(dto.getValor());
        t.setDescricao(dto.getDescricao());
        t.setPessoa(p);

        Transacao retorno = transacaoRepository.save(t);

        return new TransacaoDTOget(retorno.getId(), retorno.getDescricao(), retorno.getValor(), retorno.getTipo(),
                retorno.getPessoa());
    }

    public String deletarTransacao(Long id) {
        Transacao t = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação com id " + id + " não encontrada! "));

        t.setAtivo(Boolean.FALSE);

        transacaoRepository.save(t);

        return String.format("A transação com id: " + id + " foi apagada com sucesso!");
    }
}
