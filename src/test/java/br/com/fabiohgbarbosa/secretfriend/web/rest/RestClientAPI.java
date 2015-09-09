package br.com.fabiohgbarbosa.secretfriend.web.rest;

import org.junit.Before;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for client REST APIs
 * Created by fabio on 09/09/15.
 */
public abstract class RestClientAPI {

    private String serverUrl;

    public RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        serverUrl = new URL("http://localhost:" + getPort() + getEndPoint()).toString();
        restTemplate = new TestRestTemplate();
    }

    public <T> ResponseEntity<T> post(final Object request, final Class<T> clazz) {
        return restTemplate.postForEntity(serverUrl, request, clazz);
    }

    public void put(final Object request) {
        restTemplate.put(serverUrl, request);
    }

    public <T> ResponseEntity<T> put(final Object request, final Class<T> clazz) {
        return restTemplate.exchange(serverUrl, HttpMethod.PUT, requestEntity(), clazz, request);
    }

    public <T> ResponseEntity<List<T>> findAll() {
        final ParameterizedTypeReference<List<T>> responseType = new ParameterizedTypeReference<List<T>>() {};
        return restTemplate.exchange(serverUrl, HttpMethod.GET, requestEntity(), responseType);
    }

    public void delete(final Object request) {
        restTemplate.delete(serverUrl, request);
    }

    public <T> ResponseEntity<T> delete(final Object request, final Class<T> clazz) {
        return restTemplate.exchange(serverUrl, HttpMethod.DELETE, requestEntity(), clazz, request);
    }

    private HttpEntity<?> requestEntity() {
        final HttpHeaders requestHeaders = new HttpHeaders();
        final List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(acceptableMediaTypes);
        return new HttpEntity<Object>(requestHeaders);
    }


    protected abstract int getPort();
    protected abstract String getEndPoint();
}
