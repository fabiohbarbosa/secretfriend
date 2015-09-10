package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.person.domain.PersonFixture;
import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;

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
            Person person = PersonFixture.newPerson("sortitionDTO" + i + "test.com");
            Person friendselected = PersonFixture.newPerson("sortitionDTO" + i + 10 + "test.com");

            sortitionDTOs.add(new SortitionDTO(person, friendselected));
        }
        return sortitionDTOs;
    }
}
