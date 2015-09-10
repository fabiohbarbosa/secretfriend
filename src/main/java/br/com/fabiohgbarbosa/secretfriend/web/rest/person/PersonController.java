package br.com.fabiohgbarbosa.secretfriend.web.rest.person;

import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import br.com.fabiohgbarbosa.secretfriend.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ENDPOINT RESTful /person
 * Created by fabio on 08/09/15.
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    /**
     * POST person
     *
     * @param person 'Person' to save
     */
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Long post(final @RequestBody @Valid Person person) {
        return service.save(person).getId();
    }

    /**
     * PUT person
     *
     * @param person Person to update
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT)
    public void put(final @RequestBody @Valid Person person) {
        service.update(person);
    }

    /**
     * FIND ALL Person
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Person> findAll() {
        return service.findAll();
    }

    /**
     * FIND by name and e-mail
     */
    @RequestMapping(value = "/advanced_search", method = RequestMethod.GET)
    public @ResponseBody List<Person> findByNameAndEmail(@RequestParam String search) {
        return service.findByNameOrEmail(search);
    }
    /**
     * DELETE person
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
