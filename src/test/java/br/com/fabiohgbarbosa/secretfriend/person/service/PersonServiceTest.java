package br.com.fabiohgbarbosa.secretfriend.person.service;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import br.com.fabiohgbarbosa.secretfriend.person.repository.PersonRepository;
import br.com.fabiohgbarbosa.secretfriend.person.domain.PersonFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests to PeopleService
 * Created by fabio on 08/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    @Spy
    private PersonService service;

    //~-- save
    @Test
    public void saveTest() {
        final Person person = PersonFixture.newPerson();
        person.setId(null);

        service.save(person);

        verify(service, times(1)).saveOrUpdate(person);
    }

    @Test(expected = SecretFriendServiceException.class)
    public void saveTestErrorContainingId() {
        final Person person = PersonFixture.newPerson();

        service.save(person);

        verify(service, times(1)).saveOrUpdate(person);
    }

    //~-- update
    @Test
    public void updateTest() {
        final Person person = PersonFixture.newPerson();
        service.update(person);

        verify(service, times(1)).saveOrUpdate(person);
    }

    @Test(expected = SecretFriendServiceException.class)
    public void updateTestErrorNotContainingId() {
        final Person person = PersonFixture.newPerson();
        person.setId(null);

        service.update(person);

        verify(service, times(1)).saveOrUpdate(person);
    }

    //~-- saveOrUpdate
    @Test(expected = SecretFriendServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInSave() {
        final Person person = PersonFixture.newPerson();

        final Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).save(person);

        try {
            service.saveOrUpdate(person);
        } catch (SecretFriendServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }

    @Test(expected = SecretFriendServiceException.class)
    public void changeToServiceExceptionWithHttpStatus400WhenCatchConstraintViolationExceptionInSave() {
        final Person person = PersonFixture.newPerson();

        final ConstraintViolationException exception = Mockito.mock(ConstraintViolationException.class);
        doThrow(exception).when(repository).save(person);

        try {
            service.saveOrUpdate(person);
        } catch (SecretFriendServiceException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            throw e;
        }
    }

    //~-- findAll
    @Test
    public void findAllTest() {
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test(expected = SecretFriendServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInFindAll() {
        final Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).findAll();

        try {
            service.findAll();
        } catch (SecretFriendServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }

    //~-- findAll
    @Test
    public void findByNameOrEmail() {
        String search = "Person";
        int page = 1;
        int perPage = 1;

        PageRequest pageable = new PageRequest(page, perPage);
        doReturn(new PageImpl<Person>(new ArrayList<>())).when(repository).findByNameIgnoreCaseOrEmailIgnoreCase(search, search, pageable);

        service.findByNameOrEmail(search, page, perPage);

        verify(repository, times(1)).findByNameIgnoreCaseOrEmailIgnoreCase(search, search, pageable);
    }

    @Test(expected = SecretFriendServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInFindByNameOrEmail() {
        final Exception exception = Mockito.mock(IllegalArgumentException.class);

        String search = "Person";
        int page = 1;
        int perPage = 1;

        doThrow(exception).when(repository).findByNameIgnoreCaseOrEmailIgnoreCase(search, search, new PageRequest(page, perPage));

        try {
            service.findByNameOrEmail(search, page, perPage);
        } catch (SecretFriendServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }

    //~-- delete
    @Test
    public void deleteTest() {
        final Long id = 3L;

        service.delete(id);
        verify(repository, times(1)).delete(id);
    }

    @Test(expected = SecretFriendServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInDelete() {
        final Long id = 7L;
        final Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).delete(id);

        try {
            service.delete(id);
        } catch (SecretFriendServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }
}