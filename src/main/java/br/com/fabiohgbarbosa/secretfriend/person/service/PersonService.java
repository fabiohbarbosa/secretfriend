package br.com.fabiohgbarbosa.secretfriend.person.service;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import br.com.fabiohgbarbosa.secretfriend.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Person Service
 * Created by fabio on 08/09/15.
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    @Transactional
    public Person save(final Person person) {
        if (person.getId() != null) {
            throw new SecretFriendServiceException("ID não pode preechido", HttpStatus.BAD_REQUEST);
        }
        return saveOrUpdate(person);
    }

    @Transactional
    public void update(final Person person) {
        if (person.getId() == null) {
            throw new SecretFriendServiceException("ID não pode ser nulo", HttpStatus.BAD_REQUEST);
        }
        saveOrUpdate(person);
    }

    @Transactional
    protected Person saveOrUpdate(final Person person) {
        try {
            return repository.save(person);
        } catch (DataIntegrityViolationException e) { // e-mail unique key
            throw new SecretFriendServiceException("E-mail já existe", HttpStatus.BAD_REQUEST);
        } catch (ConstraintViolationException e) { // required fields
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Person> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(final Long id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Person> findByNameOrEmail(final String search) {
        try {
            return repository.findByNameIgnoreCaseOrEmailIgnoreCase(search, search);
        } catch (Exception e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}