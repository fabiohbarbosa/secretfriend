package br.com.fabiohgbarbosa.secretfriend.web.rest.person;

import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;

import java.util.List;

/**
 * Transfer object to view
 * Created by fabio on 10/09/15.
 */
public class PersonDTO {
    private Integer totalRegister;
    private List<Person> people;

    public PersonDTO(Integer totalRegister, List<Person> people) {
        this.totalRegister = totalRegister;
        this.people = people;
    }

    public int getTotalRegister() {
        return totalRegister;
    }

    public void setTotalRegister(final int totalRegister) {
        this.totalRegister = totalRegister;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(final List<Person> people) {
        this.people = people;
    }
}
