package br.com.fabiohgbarbosa.secretfriend.web.rest.people;

import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
import br.com.fabiohgbarbosa.secretfriend.web.config.WebApplication;
import br.com.fabiohgbarbosa.secretfriend.people.domain.PeopleFixture;
import br.com.fabiohgbarbosa.secretfriend.web.rest.RestClientAPI;
import br.com.fabiohgbarbosa.secretfriend.web.rest.handler.ErrorDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration Test to REST APIs of PeopleController
 * Created by fabio on 08/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class PeopleControllerTest extends RestClientAPI {

    @Value("${local.server.port}")
    private int port;

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected String getEndPoint() {
        return "/people";
    }

    //~-- save
    @Test
    public void testSave() {
        final People people = PeopleFixture.newPeople();
        people.setId(null);

        final ResponseEntity<Long> response = post(people, Long.class);
        final HttpStatus responseStatus = response.getStatusCode();
        final Long id = response.getBody();

        assertEquals(HttpStatus.OK, responseStatus);
        assertNotNull(id);
    }

    @Test
    public void testSaveErrorDTO() {
        final People people = PeopleFixture.newPeople();
        people.setId(1L);

        final ResponseEntity<ErrorDTO> response = post(people, ErrorDTO.class);
        final HttpStatus responseStatus = response.getStatusCode();
        final ErrorDTO errorDTO = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseStatus);
        assertNotNull(errorDTO.getMessage());
    }

    //~-- update
    @Test
    public void testUpdate() {
        final People people = PeopleFixture.newPeople();
        put(people);
    }

    @Test
    public void testUpdateErrorDTO() {
        final People people = PeopleFixture.newPeople();
        people.setId(null);

        final ResponseEntity<ErrorDTO> response = put(people, ErrorDTO.class);
        final HttpStatus responseStatus = response.getStatusCode();
        final ErrorDTO errorDTO = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseStatus);
        assertNotNull(errorDTO.getMessage());
    }

    //~-- findAll
    @Test
    public void testfindAll() {
        post(PeopleFixture.newPeople("email1@test.com"), Long.class);
        post(PeopleFixture.newPeople("email2@test.com"), Long.class);

        final ResponseEntity<List<People>> peoples = findAll();
        assertTrue(peoples.getBody().size() >= 2);
    }

    //~-- delete
    @Test
    public void testDelete() {
        Long id = post(PeopleFixture.newPeople("email3@test.com"), Long.class).getBody();
        delete(id);
    }
}