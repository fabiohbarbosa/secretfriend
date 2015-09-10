package br.com.fabiohgbarbosa.secretfriend.sortition;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.people.domain.PeopleFixture;
import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
import br.com.fabiohgbarbosa.secretfriend.people.service.PeopleService;
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
    private PeopleService peopleService;

    @Mock
    private SmtpServer smtpServer;

    @Spy
    @InjectMocks
    private SortitionService service;

    //~--execute
    @Test
    public void executeTestWithOneThousandPeopleAndVerifyNotDuplicated() {
        List<People> peoples = PeopleFixture.newPeoples(2);

        doReturn(peoples).when(peopleService).findAll();

        final Map<People, People> sortition = service.execute();

        Set<Long> friendSelected = sortition.entrySet()
                .stream()
                .map(entry -> entry.getValue().getId()).collect(Collectors.toSet()
        );

        assertEquals(sortition.size(), friendSelected.size());
    }

    @Test
    public void executeTestWithTwoPeoples() {
        List<People> peoples = PeopleFixture.newPeoples(2);

        doReturn(peoples).when(peopleService).findAll();

        service.execute();

        for (People people : peoples) {
            verify(service, times(1)).findRandomPeople(eq(people.getId()), anyListOf(People.class));
        }
    }

    @Test(expected = SecretFriendServiceException.class)
    public void executeTestError() {
        doThrow(new IllegalArgumentException()).when(peopleService).findAll();
        service.execute();
    }

    @Test(expected = SecretFriendServiceException.class)
    public void executeTestErrorWithListLessThen2() {
        List<People> peoples = PeopleFixture.newPeoples(1);

        doReturn(peoples).when(peopleService).findAll();

        service.execute();
    }

    //~--findRandomPeople
    @Test
    public void findPeopleRandomForTwoPeoples() {
        List<People> peoples = PeopleFixture.newPeoples(2);
        People firstPeople = peoples.get(0);
        People secondPeople = peoples.get(1);

        People selectedPeople = service.findRandomPeople(firstPeople.getId(), peoples);

        assertEquals(secondPeople, selectedPeople);
    }

    @Test
    public void findPeopleRandomForOneThousandPeoples() {
        List<People> peoples = PeopleFixture.newPeoples(1000);

        for (People people : peoples) {
            People selectedPeople = service.findRandomPeople(people.getId(), peoples);
            assertNotEquals(people, selectedPeople);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionInFindPeopleRandomForNoPeoples() {
        List<People> peoples = new ArrayList<>();
        service.findRandomPeople(1L, peoples);
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
        reset(peopleService, smtpServer, service);
    }
}