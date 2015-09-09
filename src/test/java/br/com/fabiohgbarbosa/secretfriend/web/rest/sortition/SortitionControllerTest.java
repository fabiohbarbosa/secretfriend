package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.people.domain.PeopleFixture;
import br.com.fabiohgbarbosa.secretfriend.web.config.WebApplication;
import br.com.fabiohgbarbosa.secretfriend.web.rest.RestClientAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Integration Test to REST APIs of SortitionController
 * Created by fabio on 09/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortitionControllerTest extends RestClientAPI {

    private final String DEFAULT_ENDPOINT = "/sortition";
    private String endPoint;

    @Value("${local.server.port}")
    private int port;

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected String getEndPoint() {
        return endPoint;
    }

    private void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    //~-- get
    @Test
    public void testSuccessGet() {
        setEndPoint("/people");
        post(PeopleFixture.newPeople("emailSortitionTest2@test.com"), Long.class);
        post(PeopleFixture.newPeople("emailSortitionTest3@test.com"), Long.class);

        setEndPoint(DEFAULT_ENDPOINT);
        final ResponseEntity<List<SortitionDTO>> dtos = findAll();
        assertTrue(dtos.getBody().size() >= 2);
    }

    //~-- sendEmail
    @Test
    public void testSendEmail() {
        setEndPoint(DEFAULT_ENDPOINT+"/send_email");
        put(SortitionDTOFixture.newSortitionDTOs(4));
    }

}