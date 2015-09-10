package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Adapter objets to SortitionDTO
 * Created by fabio on 09/09/15.
 */
public class SortitionDTOAdapter {
    public static List<SortitionDTO> from(Map<People, People> peoples) {
        ArrayList<SortitionDTO> dtos = new ArrayList<>();
        for (Map.Entry<People, People> entry : peoples.entrySet()) {
            SortitionDTO sortitionDTO = new SortitionDTO(entry.getKey(), entry.getValue());
            dtos.add(sortitionDTO);
        }
        return dtos;
    }
}
