package br.maxiprod.api_selecao.repository;

import br.maxiprod.api_selecao.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
