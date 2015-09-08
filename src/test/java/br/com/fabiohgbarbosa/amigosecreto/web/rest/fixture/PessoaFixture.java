package br.com.fabiohgbarbosa.amigosecreto.web.rest.fixture;

import br.com.fabiohgbarbosa.amigosecreto.pessoa.domain.entity.Pessoa;

/**
 * Created by fabio on 08/09/15.
 */
public class PessoaFixture {
    public static Pessoa newPessoa() {
        return new Pessoa(1L, "Fabio", "fabiohbarbosa@gmail.com");
    }
}
