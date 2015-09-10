package br.com.fabiohgbarbosa.secretfriend.sortition;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.person.domain.PersonFixture;
import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import br.com.fabiohgbarbosa.secretfriend.person.service.PersonService;
import br.com.fabiohgbarbosa.secretfriend.smtpserver.SmtpServer;
import br.com.fabiohgbarbosa.secretfriend.web.rest.sortition.SortitionDTO;
import br.com.fabiohgbarbosa.secretfriend.web.rest.sortition.SortitionDTOFixture;
import org.apache.commons.mail.EmailException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Sortition Secret Friends Unit Test
 * Created by fabio on 09/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class SortitionServiceTest {
    @Mock
    private PersonService personService;

    @Mock
    private SmtpServer smtpServer;

    @Spy
    @InjectMocks
    private SortitionService service;

    //~--execute
    @Test
    public void executeTestWithOneThousandPeopleAndVerifyNotDuplicated() {
        List<Person> persons = PersonFixture.newPeople(2);

        doReturn(persons).when(personService).findAll();

        final Map<Person, Person> sortition = service.execute();

        Set<Long> friendSelected = sortition.entrySet()
                .stream()
                .map(entry -> entry.getValue().getId()).collect(Collectors.toSet()
        );

        assertEquals(sortition.size(), friendSelected.size());
    }

    @Test
    public void executeTestWithTwoPeoples() {
        List<Person> persons = PersonFixture.newPeople(2);

        doReturn(persons).when(personService).findAll();

        service.execute();

        for (Person person : persons) {
            verify(service, times(1)).findRandomPeople(eq(person.getId()), anyListOf(Person.class));
        }
    }

    @Test(expected = SecretFriendServiceException.class)
    public void executeTestError() {
        doThrow(new IllegalArgumentException()).when(personService).findAll();
        service.execute();
    }

    @Test(expected = SecretFriendServiceException.class)
    public void executeTestErrorWithListLessThen2() {
        List<Person> persons = PersonFixture.newPeople(1);

        doReturn(persons).when(personService).findAll();

        service.execute();
    }

    //~--findRandomPeople
    @Test
    public void findPeopleRandomForTwoPeoples() {
        List<Person> persons = PersonFixture.newPeople(2);
        Person firstPerson = persons.get(0);
        Person secondPerson = persons.get(1);

        Person selectedPerson = service.findRandomPeople(firstPerson.getId(), persons);

        assertEquals(secondPerson, selectedPerson);
    }

    @Test
    public void findPeopleRandomForOneThousandPeoples() {
        List<Person> persons = PersonFixture.newPeople(1000);

        for (Person person : persons) {
            Person selectedPerson = service.findRandomPeople(person.getId(), persons);
            assertNotEquals(person, selectedPerson);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionInFindPeopleRandomForNoPeoples() {
        List<Person> persons = new ArrayList<>();
        service.findRandomPeople(1L, persons);
    }

    //~--sendEmail
    @Test
    public void testSendEmail() throws EmailException {
        final List<SortitionDTO> sortitionDTOs = SortitionDTOFixture.newSortitionDTOs(10);
        service.sendEmail(sortitionDTOs);

        verify(smtpServer, times(1)).startServer();
        verify(smtpServer, times(sortitionDTOs.size())).sendEmail(anyString(), anyString(), anyString());
    }

    @Test(expected = SecretFriendServiceException.class)
    public void testSendEmailError() throws EmailException {
        final List<SortitionDTO> sortitionDTOs = SortitionDTOFixture.newSortitionDTOs(10);

        doThrow(new EmailException("Error to send e-mail")).when(smtpServer).sendEmail(anyString(), anyString(), anyString());

        service.sendEmail(sortitionDTOs);
    }

    @After
    public void tearDown() {
        reset(personService, smtpServer, service);
    }
}