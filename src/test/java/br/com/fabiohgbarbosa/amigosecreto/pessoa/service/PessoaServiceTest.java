package br.com.fabiohgbarbosa.amigosecreto.pessoa.service;

import br.com.fabiohgbarbosa.amigosecreto.exception.ServiceException;
import br.com.fabiohgbarbosa.amigosecreto.pessoa.repository.PessoaRepository;
import br.com.fabiohgbarbosa.amigosecreto.web.rest.fixture.PessoaFixture;
import br.com.fabiohgbarbosa.amigosecreto.pessoa.domain.entity.Pessoa;
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
 * Created by fabio on 08/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository repository;

    @InjectMocks
    @Spy
    private PessoaService service;

    //~-- save
    @Test
    public void saveTest() {
        Pessoa pessoa = PessoaFixture.newPessoa();
        service.save(pessoa);

        verify(repository, times(1)).save(pessoa);
    }

    @Test(expected = ServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInSave() {
        Pessoa pessoa = PessoaFixture.newPessoa();

        Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).save(pessoa);

        try {
            service.save(pessoa);
        } catch (ServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }

    @Test(expected = ServiceException.class)
    public void changeToServiceExceptionWithHttpStatus400WhenCatchConstraintViolationExceptionInSave() {
        Pessoa pessoa = PessoaFixture.newPessoa();

        final ConstraintViolationException exception = Mockito.mock(ConstraintViolationException.class);
        doThrow(exception).when(repository).save(pessoa);

        try {
            service.save(pessoa);
        } catch (ServiceException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            throw e;
        }
    }

    //~-- update
    @Test
    public void updateTest() {
        Pessoa pessoa = PessoaFixture.newPessoa();

        doReturn(pessoa).when(repository).findOne(pessoa.getId());

        service.update(pessoa);

        verify(repository, times(1)).save(pessoa);
    }

    @Test(expected = ServiceException.class)
    public void throwServiceExceptionWithHttpStatus400WhenIdIsNull() {
        Pessoa pessoa = PessoaFixture.newPessoa();
        pessoa.setId(null);

        try {
            service.update(pessoa);
        } catch (ServiceException e) {
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

    @Test(expected = ServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInFindAll() {
        Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).findAll();

        try {
            service.findAll();
        } catch (ServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }

    //~-- findAll
    @Test
    public void deleteTest() {
        Long id = 3L;

        service.delete(id);
        verify(repository, times(1)).delete(id);
    }

    @Test(expected = ServiceException.class)
    public void changeToServiceExceptionWithHttpStatus500WhenCatchExceptionInDelete() {
        Long id = 7L;
        Exception exception = Mockito.mock(IllegalArgumentException.class);
        doThrow(exception).when(repository).delete(id);

        try {
            service.delete(id);
        } catch (ServiceException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            throw e;
        }
    }
}