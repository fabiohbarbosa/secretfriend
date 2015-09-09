package br.com.fabiohgbarbosa.secretfriend.web.rest.people;

import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
import br.com.fabiohgbarbosa.secretfriend.people.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ENDPOINT RESTful /people
 * Created by fabio on 08/09/15.
 */
@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService service;

    /**
     * POST people
     *
     * @param people 'People' to save
     */
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Long post(final @RequestBody @Valid People people) {
        return service.save(people).getId();
    }

    /**
     * PUT people
     *
     * @param people 'People' to update
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT)
    public void update(final @RequestBody @Valid People people) {
        service.update(people);
    }

    /**
     * FIND ALL people
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<People> findAll() {
        return service.findAll();
    }

    /**
     * DELETE people
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
