package br.com.fabiohgbarbosa.amigosecreto.web.rest;

import br.com.fabiohgbarbosa.amigosecreto.web.rest.handler.ErrorDTO;
import br.com.fabiohgbarbosa.amigosecreto.pessoa.domain.entity.Pessoa;
import br.com.fabiohgbarbosa.amigosecreto.web.config.WebApplication;
import br.com.fabiohgbarbosa.amigosecreto.web.rest.fixture.PessoaFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by fabio on 08/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class PessoaControllerTest {

    private RestTemplate restTemplate;

    @Value("${local.server.port}")
    private int port;

    private URL serverUrl;

    @Before
    public void setUp() throws Exception {
        serverUrl = new URL("http://localhost:" + port + "/pessoa");
        restTemplate = new TestRestTemplate();
    }

    //~-- save
    @Test
    public void saveTest() {
        Pessoa pessoa = PessoaFixture.newPessoa();
        ResponseEntity<Long> response = restTemplate.postForEntity(serverUrl.toString(), pessoa, Long.class);

        HttpStatus returnedStatus = response.getStatusCode();
        Long id = response.getBody();

        assertEquals(HttpStatus.OK, returnedStatus);
        assertNotNull(id);
    }

    @Test
    public void verifyEnvelopeErrorInSave() {
        Pessoa pessoa = PessoaFixture.newPessoa();
        pessoa.setNome(null);

        ResponseEntity<ErrorDTO> response = restTemplate.postForEntity(serverUrl.toString(), pessoa, ErrorDTO.class);

        HttpStatus returnedStatus = response.getStatusCode();
        ErrorDTO errorDTO = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, returnedStatus);
        assertNotNull(errorDTO.getMessage());
    }

    //~-- update
    @Test
    public void updateTest() {
/*        Pessoa pessoa = PessoaFixture.newPessoa();
        restTemplate.put(serverUrl.toString(), pessoa);

        HttpStatus returnedStatus = response.getStatusCode();
        Pessoa returnedPessoa = response.getBody();

        assertEquals(HttpStatus.OK, returnedStatus);
        assertNotNull(returnedPessoa.getId());*/
    }

    //~-- findAll
    //~-- delete

}