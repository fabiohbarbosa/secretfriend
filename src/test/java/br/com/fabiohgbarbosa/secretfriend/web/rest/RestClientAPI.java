package br.com.fabiohgbarbosa.secretfriend.web.rest;

import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static br.com.fabiohgbarbosa.secretfriend.web.rest.util.JsonParse.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Abstract class for client REST APIs
 * Created by fabio on 09/09/15.
 */
public abstract class RestClientAPI<Controller> {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(getController()).build();
    }

    //~-- POST
    protected <T> T postResponseObject(Object data, final Class<T> clazz) throws Exception {
        return fromJson(resultPost(data), clazz);
    }

    protected <T> List<T> postResponseList(Object data, final Class<T> clazz) throws Exception {
        return fromJsonToList(resultPost(data), clazz);
    }

    private String resultPost(final Object data) throws Exception {
        final MvcResult mvcResult = mvcResultPost(data);
        return mvcResult.getResponse().getContentAsString();
    }

    private MvcResult mvcResultPost(final Object data) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(getEndPoint())
                .header("Content-Type", "application/json")
                .content(toJson(data))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    //~-- PUT
    protected void put(Object data) throws Exception {
        mvcResultPut(data, status().isNoContent());
    }

    protected <T> T putResponseObject(Object data, final Class<T> clazz) throws Exception {
        return fromJson(resultPut(data), clazz);
    }

    protected <T> List<T> putResponseList(Object data, final Class<T> clazz) throws Exception {
        return fromJsonToList(resultPut(data), clazz);
    }

    private String resultPut(final Object data) throws Exception {
        final MvcResult mvcResult = mvcResultPut(data, status().isOk());
        return mvcResult.getResponse().getContentAsString();
    }

    private MvcResult mvcResultPut(final Object data, final ResultMatcher status) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.put(getEndPoint())
                .header("Content-Type", "application/json")
                .content(toJson(data))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status)
                .andReturn();
    }

    //~-- DELETE
    protected void delete(Long id) throws Exception {
        mvcResultDelete(id, status().isNoContent());
    }

    protected <T> T deleteResponseObject(Long id, final Class<T> clazz) throws Exception {
        return fromJson(resultDelete(id), clazz);
    }

    protected <T> List<T> deleteResponseList(Long id, final Class<T> clazz) throws Exception {
        return fromJsonToList(resultDelete(id), clazz);
    }

    private String resultDelete(final Long id) throws Exception {
        final MvcResult mvcResult = mvcResultDelete(id, status().isOk());
        return mvcResult.getResponse().getContentAsString();
    }

    private MvcResult mvcResultDelete(final Long id, final ResultMatcher status) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.delete(getEndPoint()+"/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status)
                .andReturn();
    }

    //~-- GET
    protected <T> T getResponseObject(final Class<T> clazz) throws Exception {
        return fromJson(resultGet(), clazz);
    }

    protected <T> List<T> getResponseList(final Class<T> clazz) throws Exception {
        return fromJsonToList(resultGet(), clazz);
    }

    private String resultGet() throws Exception {
        final MvcResult mvcResult = mvcResultGet();
        return mvcResult.getResponse().getContentAsString();
    }

    private MvcResult mvcResultGet() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(getEndPoint())
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
    }

    protected abstract String getEndPoint();
    protected abstract Controller getController();
}
