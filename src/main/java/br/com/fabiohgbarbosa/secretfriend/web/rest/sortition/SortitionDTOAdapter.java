package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Adapter objets to SortitionDTO
 * Created by fabio on 09/09/15.
 */
public class SortitionDTOAdapter {
    public static List<SortitionDTO> from(Map<Person, Person> people) {
        ArrayList<SortitionDTO> dtos = new ArrayList<>();
        for (Map.Entry<Person, Person> entry : people.entrySet()) {
            SortitionDTO sortitionDTO = new SortitionDTO(entry.getKey(), entry.getValue());
            dtos.add(sortitionDTO);
        }
        return dtos;
    }
}
