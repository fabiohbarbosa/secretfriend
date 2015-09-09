package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Transfer object to view
 * Created by fabio on 09/09/15.
 */
public class SortitionDTO implements Serializable {
    @NotNull(message = "Pessoa não pode ser nulo")
    private People people;

    @NotNull(message = "Amigo escolhido não pode ser nulo")
    private People friendselected;

    public SortitionDTO() {
    }

    public SortitionDTO(final People people, final People friendselected) {
        this.people = people;
        this.friendselected = friendselected;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(final People people) {
        this.people = people;
    }

    public People getFriendselected() {
        return friendselected;
    }

    public void setFriendselected(final People friendselected) {
        this.friendselected = friendselected;
    }
}