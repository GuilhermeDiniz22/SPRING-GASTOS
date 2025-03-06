package br.maxiprod.api_selecao.service;

import br.maxiprod.api_selecao.dto.ConsultaDTO;
import br.maxiprod.api_selecao.dto.PessoaDTO;
import br.maxiprod.api_selecao.dto.TransacaoDTOget;
import br.maxiprod.api_selecao.models.Pessoa;
import br.maxiprod.api_selecao.models.Transacao;
import br.maxiprod.api_selecao.repository.PessoaRepository;
import br.maxiprod.api_selecao.repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    private TransacaoRepository transacaoRepository;

    public PessoaService(PessoaRepository pessoaRepository, TransacaoRepository transacaoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public PessoaDTO novaPessoa(PessoaDTO dto){
        Pessoa nova = new Pessoa();
        nova.setId(dto.getId());
        nova.setNome(dto.getNome());
        nova.setIdade(dto.getIdade());
        nova.setAtivo(Boolean.TRUE);

        Pessoa pessoa = pessoaRepository.save(nova);

        return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getIdade());
    }

    @Transactional
    public PessoaDTO atualizarPessoa(PessoaDTO dto, Long id){
        Pessoa p = pessoaRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com id" + dto.getId() + "não encontrada! "));
        p.setIdade(dto.getIdade());
        p.setNome(dto.getNome());
        p.setId(dto.getId());

        Pessoa retorno = pessoaRepository.save(p);

        return new PessoaDTO(retorno.getId(), retorno.getNome(), retorno.getIdade());
    }

    public PessoaDTO pegarPessoaPorId(Long id){
        Pessoa p = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com id" + id + "não encontrada! "));

        return new PessoaDTO(p.getId(), p.getNome(), p.getIdade());
    }

    public List<PessoaDTO> pegarPessoas(){
        List<PessoaDTO> pessoas = pessoaRepository.findAll()
                .stream()
                .map((p) -> new PessoaDTO(p.getId(), p.getNome(), p.getIdade()))
                .toList();

        return pessoas;
    }

    @Transactional
    public String deletarPessoaEtransacoes(Long id){
        Pessoa p = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com id" + id + " não encontrada! "));

        List<Transacao> transacoes = transacaoRepository.findTransacoesByPessoaId(id);

        for(Transacao transacao : transacoes){
            transacao.setAtivo(Boolean.FALSE);
            transacaoRepository.save(transacao);
        }

        p.setAtivo(Boolean.FALSE);

        pessoaRepository.save(p);


        return String.format("Pessoa com id: " + id + " deletada com sucesso!");
    }

    public ConsultaDTO consultarPessoas(){
        List<TransacaoDTOget> transacoes = transacaoRepository.findAll()
                .stream().map((t) -> new TransacaoDTOget(t.getId(), t.getDescricao(), t.getValor(), t.getTipo(), t.getPessoa()))
                .toList();

        List<PessoaDTO> pessoas = pessoaRepository.findAll()
                .stream().map((p) -> new PessoaDTO(p.getId(), p.getNome(), p.getIdade()))
                .toList();

        BigDecimal totalDespesas = BigDecimal.ZERO;

        BigDecimal totalReceitas =  BigDecimal.ZERO;

        for(TransacaoDTOget t : transacoes){
            if(Objects.equals(t.getTipo(), "despesa")){
                totalDespesas = totalDespesas.add(t.getValor());
            }else{
                totalReceitas = totalReceitas.add(t.getValor());
            }
        }


        BigDecimal saldo = totalReceitas.subtract(totalDespesas);


        return new ConsultaDTO(pessoas, totalReceitas, totalDespesas, saldo);

    }

    public ConsultaDTO consultarPessoaPorId(Long id){
        Pessoa p = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com id" + id + " não encontrada! "));

        PessoaDTO pessoa = new PessoaDTO(p.getId(), p.getNome(), p.getIdade());

        List<TransacaoDTOget> transacoes = transacaoRepository.findTransacoesByPessoaId(id)
                .stream().map((t) -> new TransacaoDTOget(t.getId(), t.getDescricao(), t.getValor(), t.getTipo(), t.getPessoa()))
                .toList();

        BigDecimal totalDespesas = BigDecimal.ZERO;

        BigDecimal totalReceitas =  BigDecimal.ZERO;

        for(TransacaoDTOget t : transacoes){
            if(Objects.equals(t.getTipo(), "despesa")){
                totalDespesas = totalDespesas.add(t.getValor());
            }else{
                totalReceitas = totalReceitas.add(t.getValor());
            }
        }


        BigDecimal saldo = totalReceitas.subtract(totalDespesas);


        return new ConsultaDTO(pessoa, totalReceitas, totalDespesas, saldo);
    }
}
