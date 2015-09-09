package br.com.fabiohgbarbosa.secretfriend.people.service;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
import br.com.fabiohgbarbosa.secretfriend.people.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * People Service
 * Created by fabio on 08/09/15.
 */
@Service
public class PeopleService {
    @Autowired
    private PeopleRepository repository;

    @Transactional
    public People save(final People people) {
        if (people.getId() != null) {
            throw new SecretFriendServiceException("ID não pode preechido", HttpStatus.BAD_REQUEST);
        }
        return saveOrUpdate(people);
    }

    @Transactional
    public void update(final People people) {
        if (people.getId() == null) {
            throw new SecretFriendServiceException("ID não pode ser nulo", HttpStatus.BAD_REQUEST);
        }
        saveOrUpdate(people);
    }

    @Transactional
    protected People saveOrUpdate(final People people) {
        try {
            return repository.save(people);
        } catch (ConstraintViolationException e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<People> findAll() {
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
}