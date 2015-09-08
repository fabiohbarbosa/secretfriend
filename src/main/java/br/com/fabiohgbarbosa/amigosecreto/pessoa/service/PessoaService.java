package br.com.fabiohgbarbosa.amigosecreto.pessoa.service;

import br.com.fabiohgbarbosa.amigosecreto.exception.ServiceException;
import br.com.fabiohgbarbosa.amigosecreto.pessoa.repository.PessoaRepository;
import br.com.fabiohgbarbosa.amigosecreto.pessoa.domain.entity.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by fabio on 08/09/15.
 */
@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        Long id = pessoa.getId();
        if (id == null) {
            throw new ServiceException("ID não pode preechido", HttpStatus.BAD_REQUEST);
        }

        try {
            return repository.save(pessoa);
        } catch (ConstraintViolationException e) {
            throw new ServiceException(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void update(Pessoa pessoa) {
        Long id = pessoa.getId();
        if (id == null) {
            throw new ServiceException("ID não pode ser nulo", HttpStatus.BAD_REQUEST);
        }
        save(pessoa);
    }

    @Transactional(readOnly = true)
    public List<Pessoa> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}