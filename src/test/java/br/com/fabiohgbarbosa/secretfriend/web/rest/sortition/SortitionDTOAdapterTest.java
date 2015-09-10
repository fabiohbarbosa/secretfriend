package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.person.domain.PersonFixture;
import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tests adapter objets to SortitionDTO
 * Created by fabio on 09/09/15.
 */
public class SortitionDTOAdapterTest {

    @Test
    public void testZeroPeopleFrom() {
        int size = 0;

        List<SortitionDTO> dtos = SortitionDTOAdapter.from(getPeoples(size));

        assertEquals(size, dtos.size());
    }

    @Test
    public void testOnePeopleFrom() {
        int size = 1;

        List<SortitionDTO> dtos = SortitionDTOAdapter.from(getPeoples(size));

        assertEquals(size, dtos.size());
    }

    @Test
    public void testOneThousandPeopleFrom() {
        int size = 1000;

        List<SortitionDTO> dtos = SortitionDTOAdapter.from(getPeoples(size));

        assertEquals(size, dtos.size());
    }

    public Map<Person, Person> getPeoples(int size) {
        Map<Person, Person> people = new HashMap<>();

        for (int i = 0; i < size ; i++) {
            Person person = PersonFixture.newPerson("person" + i + "@test.com");
            Person friendselected = PersonFixture.newPerson("friendselected" + i + "@test.com");
            people.put(person, friendselected);
        }

        return people;
    }
}