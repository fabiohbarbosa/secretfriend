package br.com.fabiohgbarbosa.secretfriend.people.service;

import br.com.fabiohgbarbosa.secretfriend.exception.AmigoSecretoServiceException;
import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
import br.com.fabiohgbarbosa.secretfriend.people.repository.PeopleRepository;
import br.com.fabiohgbarbosa.secretfriend.web.rest.fixture.PeopleFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests to PeopleService
 * Created by fabio on 08/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PeopleServiceTest {

    @Mock
    private PeopleRepository repository;

    @InjectMocks
    @Spy
    private PeopleService service;

    //~-- save
    @Test
    public void saveTest() {
        final People people = PeopleFixture.newPeople();
        people.setId(null);

        service.save(people);

        verify(service, times(1)).saveOrUpdate(people);
    }

    @Test(expected = AmigoSecretoServiceException.class)
    public void saveTestErrorContainingId() {
        final People people = PeopleFixture.newPeople();

        service.save(people);

        verify(service, times(1)).saveOrUpdate(people);
    }

    //~-- update
    @Test
    public void updateTest() {
        final People people = PeopleFixture.newPeople();
        service.update(people);

        verify(service, times(1)).saveOrUpdate(people);
    }

    @Test(expected = AmigoSecretoServiceException.class)
    public void updateTestErrorNotContainingId() {
        final People people = PeopleFixture.newPeople();
        people.setId(null);

        service.update(people);

        verify(service, times(1)).saveOrUpdate(people);
    }

    //~-- saveOrUpdate
    @Test(expected = AmigoSecretoServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInSave() {
        final People people = PeopleFixture.newPeople();

        final Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).save(people);

        try {
            service.saveOrUpdate(people);
        } catch (AmigoSecretoServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }

    @Test(expected = AmigoSecretoServiceException.class)
    public void changeToServiceExceptionWithHttpStatus400WhenCatchConstraintViolationExceptionInSave() {
        final People people = PeopleFixture.newPeople();

        final ConstraintViolationException exception = Mockito.mock(ConstraintViolationException.class);
        doThrow(exception).when(repository).save(people);

        try {
            service.saveOrUpdate(people);
        } catch (AmigoSecretoServiceException e) {
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

    @Test(expected = AmigoSecretoServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInFindAll() {
        final Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).findAll();

        try {
            service.findAll();
        } catch (AmigoSecretoServiceException e) {
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

    @Test(expected = AmigoSecretoServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInDelete() {
        final Long id = 7L;
        final Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).delete(id);

        try {
            service.delete(id);
        } catch (AmigoSecretoServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }
}