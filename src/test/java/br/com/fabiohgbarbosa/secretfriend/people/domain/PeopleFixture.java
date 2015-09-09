package br.com.fabiohgbarbosa.secretfriend.people.domain;

import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;

import java.util.ArrayList;
import java.util.List;

/**
 * People fixture for tests
 * Created by fabio on 08/09/15.
 */
public class PeopleFixture {
    public static People newPeople() {
        return new People(1L, "Primeira People", "firstpeople@test.com");
    }

    public static People newPeople(final String email) {
        return new People("People", email);
    }

    public static List<People> newPeoples(int size) {
        List<People> peoples = new ArrayList<People>();

        for (int i = 0; i < size; i++) {
            peoples.add(new People((long) i, "Name"+i, "people"+i+"@test.com"));
        }
        return peoples;
    }
}
