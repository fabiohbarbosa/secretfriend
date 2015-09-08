package br.com.fabiohgbarbosa.amigosecreto.web.rest;

import br.com.fabiohgbarbosa.amigosecreto.pessoa.domain.entity.Pessoa;
import br.com.fabiohgbarbosa.amigosecreto.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ENDPOINT RESTful /pessoa
 * Created by fabio on 08/09/15.
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService service;

    /**
     * POST pessoa
     * @param pessoa 'Pessoa' to save
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Long post(final @RequestBody @Valid Pessoa pessoa) {
        return service.save(pessoa).getId();
    }

    /**
     * PUT pessoa
     * @param pessoa 'Pessoa' to update
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT)
    public void update(final @RequestBody @Valid Pessoa pessoa) {
        service.update(pessoa);
    }

    /**
     * FIND ALL pessoa
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Pessoa> findAll() {
        return service.findAll();
    }

    /**
     * DELETE pessoa
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(Long id) {
        service.delete(id);
    }
}
