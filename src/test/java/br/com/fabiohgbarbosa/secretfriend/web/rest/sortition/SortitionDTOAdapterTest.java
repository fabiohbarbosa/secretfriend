package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.people.domain.PeopleFixture;
import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
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

    public Map<People, People> getPeoples(int size) {
        Map<People, People> peoples = new HashMap<>();

        for (int i = 0; i < size ; i++) {
            People people = PeopleFixture.newPeople("people"+i+"@test.com");
            People friendselected = PeopleFixture.newPeople("friendselected"+i+"@test.com");
            peoples.put(people, friendselected);
        }

        return peoples;
    }
}