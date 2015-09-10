package br.com.fabiohgbarbosa.secretfriend.person.domain;

import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Person fixture for tests
 * Created by fabio on 08/09/15.
 */
public class PersonFixture {
    public static Person newPerson() {
        return new Person(1L, "Primeira Person", "firstperson@test.com");
    }

    public static Person newPerson(final String email) {
        return new Person("Person", email);
    }

    public static List<Person> newPeople(int size) {
        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < size; i++) {
            persons.add(new Person((long) i, "Name"+i, "person"+i+"@test.com"));
        }
        return persons;
    }
}
