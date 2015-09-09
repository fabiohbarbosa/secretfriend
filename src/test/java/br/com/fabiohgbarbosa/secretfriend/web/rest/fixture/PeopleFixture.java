package br.com.fabiohgbarbosa.secretfriend.web.rest.fixture;

import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;

/**
 * Created by fabio on 08/09/15.
 */
public class PeopleFixture {
    public static People newPeople() {
        return new People(1L, "Primeira People", "firstpeople@test.com");
    }

    public static People newPeople(final String email) {
        return new People("First People", email);
    }
}
