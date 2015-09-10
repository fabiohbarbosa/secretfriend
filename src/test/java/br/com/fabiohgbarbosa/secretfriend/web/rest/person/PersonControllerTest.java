package br.com.fabiohgbarbosa.secretfriend.web.rest.person;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.person.domain.PersonFixture;
import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import br.com.fabiohgbarbosa.secretfriend.web.config.WebApplication;
import br.com.fabiohgbarbosa.secretfriend.web.rest.RestClientAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.NestedServletException;

import static org.junit.Assert.assertNotNull;

/**
 * Integration test to API RESTful /person
 * Created by fabio on 10/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
public class PersonControllerTest extends RestClientAPI<PersonController> {

    @Autowired
    private PersonController controller;

    //~-- post
    @Test
    public void testPost() throws Exception {
        Person newPerson = PersonFixture.newPerson("testPost@test.com");
        assertNotNull(postResponseObject(newPerson, Long.class));
    }

    @Test(expected = SecretFriendServiceException.class)
    public void testErrorPostWhenPersonContainId() throws Throwable {
        Person newPerson = PersonFixture.newPerson("testErrorPost@test.com");
        newPerson.setId(1L);

        try {
            postResponseObject(newPerson, Long.class);
        } catch(NestedServletException e) {
            throw e.getCause();
        }
    }

    //~-- put
    @Test
    public void testPut() throws Exception {
        Person newPerson = PersonFixture.newPerson("testPut@test.com");
        Long id = postResponseObject(newPerson, Long.class);
        newPerson.setId(id);
        put(newPerson);
    }

    @Test(expected = SecretFriendServiceException.class)
    public void testErrorPutWhenPersonNotContainId() throws Throwable {
        Person newPerson = PersonFixture.newPerson("testErrorPut@test.com");

        try {
            put(newPerson);
        } catch(NestedServletException e) {
            throw e.getCause();
        }
    }

    //~-- findAll
    @Test
    public void testFindAll() throws Exception {
        assertNotNull(getResponseList(Person.class));
    }

    //~-- delete
    @Test
    public void testDelete() throws Exception {
        Person newPerson = PersonFixture.newPerson("testDelete@test.com");
        Long id = postResponseObject(newPerson, Long.class);
        delete(id);
    }

    @Override
    protected String getEndPoint() {
        return "/person";
    }

    @Override
    protected PersonController getController() {
        return controller;
    }
}