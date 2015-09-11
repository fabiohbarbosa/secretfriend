package br.com.fabiohgbarbosa.secretfriend.web.rest.person;

import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import br.com.fabiohgbarbosa.secretfriend.person.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
     * FIND ALL people
     * @param page Page, start in 0
     * @param perPage Register per page
     * @return People
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PersonDTO findAll(@RequestParam Integer page, @RequestParam Integer perPage) {
        return service.findAll(page, perPage);
    }

    /**
     * FIND by name and e-mail
     */
    @RequestMapping(value = "/advanced_search", method = RequestMethod.GET)
    public @ResponseBody PersonDTO findByNameAndEmail(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer perPage) {
        return service.findByNameOrEmail(search, page, perPage);
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
