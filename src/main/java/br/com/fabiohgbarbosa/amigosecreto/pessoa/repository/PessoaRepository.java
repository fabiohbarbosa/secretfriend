package br.com.fabiohgbarbosa.amigosecreto.pessoa.repository;

import br.com.fabiohgbarbosa.amigosecreto.pessoa.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fabio on 08/09/15.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
