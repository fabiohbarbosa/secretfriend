package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.people.domain.PeopleFixture;
import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;

import java.util.ArrayList;
import java.util.List;

/**
 * SortitionDTO fixture for tests
 * Created by fabio on 09/09/15.
 */
public class SortitionDTOFixture {
    public static List<SortitionDTO> newSortitionDTOs(int size) {
        List<SortitionDTO> sortitionDTOs = new ArrayList<>();
        for (int i = 0; i < size ; i++) {
            People people = PeopleFixture.newPeople("sortitionDTO"+i+"test.com");
            People friendselected = PeopleFixture.newPeople("sortitionDTO"+i+10+"test.com");

            sortitionDTOs.add(new SortitionDTO(people, friendselected));
        }
        return sortitionDTOs;
    }
}
